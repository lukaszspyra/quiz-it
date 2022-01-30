package spyra.lukasz.javaquizzes.feature.jsonparser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class JsonCustomDeserializer extends JsonDeserializer<QuizJson> {

    public static final String TITLE = "title";
    public static final String MAX_SCORE = "maxScore";
    public static final String CREATED = "created";
    public static final String UPDATED = "updated";
    public static final String QUESTIONS = "questions";
    public static final String SCORE = "score";
    public static final String ANSWERS = "answers";
    public static final String CORRECT = "correct";


    @Override
    public QuizJson deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        JsonNode node = jsonParser.readValueAsTree();
        QuizJson quizJson = new QuizJson();
        quizJson.setTitle(node.get(TITLE).textValue());
        quizJson.setMaxScore(node.get(MAX_SCORE).asInt());
        LocalDateTime now = LocalDateTime.now();
        quizJson.setCreated(LocalDateTime.parse(node.get(CREATED).asText(now.toString())));
        quizJson.setUpdated(LocalDateTime.parse(node.get(UPDATED).asText(now.toString())));
        Iterator<JsonNode> jsonNodeQuestionsIterator = node.get(QUESTIONS).elements();
        List<QuestionJson> questionsJson = parseQuestions(jsonNodeQuestionsIterator);
        quizJson.setQuestions(questionsJson);
        return quizJson;
    }

    private List<QuestionJson> parseQuestions(Iterator<JsonNode> jsonNodeQuestionsIterator) {
        List<QuestionJson> questionsJson = new ArrayList<>();
        while (jsonNodeQuestionsIterator.hasNext()) {
            JsonNode nextQuestion = jsonNodeQuestionsIterator.next();
            String title = nextQuestion.get(TITLE).asText();
            int score = nextQuestion.get(SCORE).asInt();
            Iterator<JsonNode> jsonNodeAnswersIterator = nextQuestion.get(ANSWERS).elements();
            List<AnswerJson> answers = parseAnswers(jsonNodeAnswersIterator);
            QuestionJson questionJson = new QuestionJson(title, score, answers);
            questionsJson.add(questionJson);

        }
        return questionsJson;
    }

    private List<AnswerJson> parseAnswers(Iterator<JsonNode> jsonNodeAnswersIterator) {
        List<AnswerJson> answers = new ArrayList<>();
        while (jsonNodeAnswersIterator.hasNext()) {
            JsonNode nextAnswer = jsonNodeAnswersIterator.next();
            String ansTitle = nextAnswer.get(TITLE).asText();
            boolean isCorrect = nextAnswer.get(CORRECT).asBoolean();
            AnswerJson answerJson = new AnswerJson(ansTitle, isCorrect);
            answers.add(answerJson);
        }
        return answers;
    }
}
