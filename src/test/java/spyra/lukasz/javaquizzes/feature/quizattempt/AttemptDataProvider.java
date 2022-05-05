package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.testng.annotations.DataProvider;
import spyra.lukasz.javaquizzes.shared.Answer;
import spyra.lukasz.javaquizzes.shared.Question;

import java.util.List;

public class AttemptDataProvider {

    private static Answer answer1 = new Answer(1, true, "answer1", null);
    private static Answer answer2 = new Answer(2, true, "answer2", null);
    private static Answer answer3 = new Answer(3, false, "answer3", new Question());
    private static AnswerView answerView1 = new AnswerView(1, true, "answer1");
    private static AnswerView answerView2 = new AnswerView(2, true, "answer2");
    private static AnswerView answerView3 = new AnswerView(3, false, "answer3");

    private static Question question1 = new Question(7, 0, 1, "question1", null, List.of(answer1));
    private static Question question2 = new Question(8, 0, 2, "question2", null, List.of(answer1, answer2));
    private static Question question3 = new Question(9, 0, 3, "question3", null, List.of(answer1, answer2, answer3));
    private static QuestionView questionView1 = new QuestionView(7, 1, "question1", List.of(answerView1));
    private static QuestionView questionView2 = new QuestionView(8, 2, "question2", List.of(answerView1, answerView2));
    private static QuestionView questionView3 = new QuestionView(9, 3, "question3", List.of(answerView1, answerView2, answerView3));

    @DataProvider
    public static Object[][] answersWithCorrespondingDTOs() {
        return new Object[][]{
                {answer1, answerView1},
                {answer2, answerView2},
                {answer3, answerView3},
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
}
