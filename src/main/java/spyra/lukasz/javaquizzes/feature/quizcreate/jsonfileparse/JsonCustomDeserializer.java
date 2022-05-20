package spyra.lukasz.javaquizzes.feature.quizcreate.jsonfileparse;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Custom deserializer implemented to read values of API delivered JSON format
 */
class JsonCustomDeserializer extends JsonDeserializer<QuizJson> {

    @Override
    public QuizJson deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        JsonNode nodes = jsonParser.readValueAsTree();
        final Iterator<JsonNode> elements = nodes.elements();
        boolean restricted = false;
        boolean predefined = false;
        boolean demo = false;
        LocalDateTime created = LocalDateTime.now();
        if (nodes.findValue("restricted") != null) {
            final JsonNode firstNode = elements.next();
            restricted = extractBooleanTagFromNode(firstNode, JsonNodes.RESTRICTED);
            predefined = extractBooleanTagFromNode(firstNode, JsonNodes.PREDEFINED);
            demo = extractBooleanTagFromNode(firstNode, JsonNodes.DEMO);
            created = LocalDateTime.parse(firstNode.get(JsonNodes.CREATED.getValue()).asText());
        }
        List<QuestionJson> questions = parseQuestionList(elements);
        int totalScore = calculateQuizTotalScore(questions);
        String quizTitle;
        final String tag = ctxt.findInjectableValue("tag", null, null).toString();
        if (tag.isEmpty()) {
            quizTitle = createPredefinedTitle(questions);
        } else {
            quizTitle = createTitle(tag);
        }
        String quizDifficulty = getMostFrequentQuestionDifficulty(questions);
        return new QuizJson.QuizJsonBuilder()
                .setTitle(quizTitle)
                .setRestricted(restricted)
                .setPredefined(predefined)
                .setDemo(demo)
                .setMaxScore(totalScore)
                .setCreated(created)
                .setUpdated(LocalDateTime.now())
                .setDifficulty(quizDifficulty)
                .setQuestions(questions)
                .createQuizJson();
    }

    private boolean extractBooleanTagFromNode(JsonNode firstNode, JsonNodes jsonNode) {
        return firstNode.get(jsonNode.getValue()).asBoolean();
    }

    private String createPredefinedTitle(List<QuestionJson> questions) {
        return questions.stream()
                .flatMap(quest -> quest.getTags().stream())
                .distinct()
                .collect(Collectors.joining("-"));
    }

    private String createTitle(final String tag) {
        return "Random-" + tag;
    }

    private List<QuestionJson> parseQuestionList(Iterator<JsonNode> elements) {
        List<QuestionJson> questions = new LinkedList<>();
        while (elements.hasNext()) {
            questions.add(parseQuestion(elements.next()));
        }
        return questions;
    }

    private int calculateQuizTotalScore(List<QuestionJson> questions) {
        return questions.parallelStream()
                .reduce(0, (subtotal, element) -> subtotal + element.getScore(), Integer::sum);
    }

    private String getMostFrequentQuestionDifficulty(List<QuestionJson> questions) {
        return questions.stream()
                .collect(Collectors.groupingBy(QuestionJson::getDifficulty, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .get()
                .getKey();
    }


    private QuestionJson parseQuestion(JsonNode node) {
        final QuestionJson.Builder builder = new QuestionJson.Builder();
        final List<AnswerJson> answers = parseAnswers(node);
        return builder.withApiId(node.get(JsonNodes.ID.getValue()).asLong())
                .withContent(node.get(JsonNodes.QUESTION.getValue()).asText())
                .withAnswers(answers)
                .withTags(parseTags(node))
                .withScore(questionScore(answers))
                .withDifficulty(node.get(JsonNodes.DIFFICULTY.getValue()).asText().toUpperCase())
                .build();
    }

    private List<String> parseTags(JsonNode node) {
        List<String> tags = new ArrayList<>();
        final Iterator<JsonNode> elements = node.get(JsonNodes.TAGS.getValue()).elements();
        while (elements.hasNext()) {
            tags.add(elements.next().get(JsonNodes.NAME.getValue()).asText());
        }
        return tags;
    }

    private int questionScore(List<AnswerJson> answers) {
        return Math.toIntExact(answers.stream()
                .filter(AnswerJson::isCorrect)
                .count());
    }

    private List<AnswerJson> parseAnswers(JsonNode node) {
        final Iterator<JsonNode> answersIterator = node.get(JsonNodes.ANSWERS.getValue()).elements();
        final Iterator<JsonNode> isCorrectIterator = node.get(JsonNodes.CORRECT_ANSWERS.getValue()).elements();
        List<AnswerJson> answers = new ArrayList<>();
        while (answersIterator.hasNext()) {
            final JsonNode answer = answersIterator.next();
            if (answer.getNodeType() == JsonNodeType.NULL) {
                continue;
            }
            String answerContent = answer.asText();
            AnswerJson answerJson = new AnswerJson(answerContent, isCorrectIterator.next().asBoolean());
            answers.add(answerJson);
        }
        return answers;
    }
}
