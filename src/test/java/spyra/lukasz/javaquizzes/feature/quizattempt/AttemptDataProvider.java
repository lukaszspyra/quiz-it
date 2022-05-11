package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.testng.annotations.DataProvider;
import spyra.lukasz.javaquizzes.shared.Answer;
import spyra.lukasz.javaquizzes.shared.Question;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.time.LocalDateTime;
import java.util.List;

public class AttemptDataProvider {

    private static final LocalDateTime MOCKED_DATE_TIME_1 = LocalDateTime.now();
    private static final LocalDateTime MOCKED_DATE_TIME_2 = LocalDateTime.now();

    private static Answer answer1 = new Answer(1, true, "answer1", null);
    private static Answer answer2 = new Answer(2, true, "answer2", null);
    private static Answer answer3 = new Answer(3, false, "answer3", new Question());
    private static AnswerView answerView1 = new AnswerView(1, true, "answer1");
    private static AnswerView answerView2 = new AnswerView(2, true, "answer2");
    private static AnswerView answerView3 = new AnswerView(3, false, "answer3");

    private static Question question1 = new Question(7, 0, 1, "question1", "Medium", null, List.of(answer1));
    private static Question question2 = new Question(8, 0, 2, "question2", "Hard", null, List.of(answer1, answer2));
    private static Question question3 = new Question(9, 0, 3, "question3", "Easy", null, List.of(answer1, answer2, answer3));
    private static QuestionView questionView1 = new QuestionView(7, 1, "question1", "Medium", List.of(answerView1));
    private static QuestionView questionView2 = new QuestionView(8, 2, "question2", "Hard", List.of(answerView1, answerView2));
    private static QuestionView questionView3 = new QuestionView(9, 3, "question3", "Easy", List.of(answerView1, answerView2, answerView3));

    private static AttemptView attemptView1 = new AttemptView(0, "attempt1", 10, MOCKED_DATE_TIME_1, MOCKED_DATE_TIME_1, List.of(questionView1));
    private static AttemptView attemptView2 = new AttemptView(1, "attempt2", 12, MOCKED_DATE_TIME_2, MOCKED_DATE_TIME_2, List.of(questionView1, questionView2));
    private static AttemptView attemptView3 = new AttemptView(2, "attempt3", 15, MOCKED_DATE_TIME_1, MOCKED_DATE_TIME_2, List.of(questionView1, questionView2, questionView3));
    private static Quiz quiz1 = new Quiz(0, "attempt1", 10, MOCKED_DATE_TIME_1, MOCKED_DATE_TIME_1, false, false, "Easy", List.of(question1));
    private static Quiz quiz2 = new Quiz(1, "attempt2", 12, MOCKED_DATE_TIME_2, MOCKED_DATE_TIME_2, false, false, "Medium", List.of(question1, question2));
    private static Quiz quiz3 = new Quiz(2, "attempt3", 15, MOCKED_DATE_TIME_1, MOCKED_DATE_TIME_2, false, false, "Hard", List.of(question1, question2, question3));


    @DataProvider
    public static Object[][] answersWithCorrespondingDTOs() {
        return new Object[][]{
                {answer1, answerView1},
                {answer2, answerView2},
                {answer3, answerView3},
        };
    }

    @DataProvider
    public static Object[][] answersWithRandomDTOs() {
        return new Object[][]{
                {answer1, answerView3},
                {answer2, answerView1},
                {answer3, answerView1},
        };
    }

    @DataProvider
    public static Object[][] questionsWithCorrespondingDTOs() {
        return new Object[][]{
                {question1, questionView1},
                {question2, questionView2},
                {question3, questionView3}
        };
    }

    @DataProvider
    public static Object[][] questionsWithRandomDTOs() {
        return new Object[][]{
                {question1, questionView2},
                {question2, questionView3},
                {question3, questionView2}
        };
    }

    @DataProvider
    public static Object[][] quizzesWithCorrespondingAttemptDTOs() {
        return new Object[][]{
                {quiz1, attemptView1},
                {quiz2, attemptView2},
                {quiz3, attemptView3}
        };
    }

    @DataProvider
    public static Object[][] quizzesWithRandomAttemptDTOs() {
        return new Object[][]{
                {quiz1, attemptView2},
                {quiz2, attemptView3},
                {quiz3, attemptView1}
        };
    }
}
