package spyra.lukasz.javaquizzes.feature.jsonparser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonParser {

    public static final String TITLE = "title";
    public static final String MAX_SCORE = "maxScore";
    public static final String CREATED = "created";
    public static final String UPDATED = "updated";
    public static final String QUESTIONS = "questions";
    public static final String SCORE = "score";
    public static final String ANSWERS = "answers";
    public static final String CORRECT = "correct";

    public QuizJson parse(Path json) throws IOException {
        JsonNode node = readTreeWithLocalDateTime(json);
        QuizJson quizJson = new QuizJson();
        quizJson.setTitle(node.get(TITLE).textValue());
        quizJson.setMaxScore(node.get(MAX_SCORE).asInt());
        quizJson.setCreated(LocalDateTime.parse(node.get(CREATED).asText()));
        quizJson.setUpdated(LocalDateTime.parse(node.get(UPDATED).asText()));
        Iterator<JsonNode> jsonNodeQuestionsIterator = node.get(QUESTIONS).elements();
        List<QuestionJson> questionsJson = parseQuestions(jsonNodeQuestionsIterator);
        quizJson.setQuestions(questionsJson);
        return quizJson;
    }

    private JsonNode readTreeWithLocalDateTime(Path json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readTree(json.toFile());
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
