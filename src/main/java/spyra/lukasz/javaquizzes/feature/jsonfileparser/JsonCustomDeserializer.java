package spyra.lukasz.javaquizzes.feature.jsonfileparser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class JsonCustomDeserializer extends JsonDeserializer<QuizJson> {

    //TODO:set quiz title and restricted, reuse existing questions in database
    @Override
    public QuizJson deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        QuizJson quizJson = new QuizJson();
        List<QuestionJson> questions = new LinkedList<>();
        JsonNode node = jsonParser.readValueAsTree();
        final Iterator<JsonNode> elements = node.elements();
        while (elements.hasNext()) {
            questions.add(parseQuestion(elements.next()));
        }
        quizJson.setQuestions(questions);
        int totalScore = questions.parallelStream()
                .reduce(0, (subtotal, element) -> subtotal + element.getScore(), Integer::sum);
        quizJson.setMaxScore(totalScore);
        LocalDateTime now = LocalDateTime.now();
        quizJson.setCreated(LocalDateTime.parse(now.toString()));
        quizJson.setUpdated(LocalDateTime.parse(now.toString()));
        return quizJson;
    }

    private QuestionJson parseQuestion(JsonNode node) {
        final long apiId = node.get(JsonNodes.ID.getValue()).asLong();
        final String content = node.get(JsonNodes.QUESTION.getValue()).asText();
        List<AnswerJson> answers = parseAnswers(node);
        final int score = Math.toIntExact(answers.stream()
                .filter(AnswerJson::isCorrect)
                .count());

        return new QuestionJson(apiId, score, content, answers);
    }

    private List<AnswerJson> parseAnswers(JsonNode node) {
        final Iterator<JsonNode> answersIterator = node.get(JsonNodes.ANSWERS.getValue()).elements();
        final Iterator<JsonNode> isCorrectIterator = node.get(JsonNodes.CORRECT_ANSWERS.getValue()).elements();
        List<AnswerJson> answers = new ArrayList<>();
        while (answersIterator.hasNext()) {
            String answerContent = answersIterator.next().asText();
            AnswerJson answerJson = new AnswerJson(answerContent, isCorrectIterator.next().asBoolean());
            answers.add(answerJson);
        }
        return answers;
    }
}
