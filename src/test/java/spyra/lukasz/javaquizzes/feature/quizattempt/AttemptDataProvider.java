package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.testng.annotations.DataProvider;
import spyra.lukasz.javaquizzes.shared.Answer;
import spyra.lukasz.javaquizzes.shared.Question;

public class AttemptDataProvider {

    @DataProvider
    public static Object[][] answersWithCorrespondingDTOs() {
        Answer answer1 = new Answer(1, true, "answer1", null);
        AnswerView view1 = new AnswerView(1, true, "answer1");

        Answer answer2 = new Answer(2, true, "answer2", null);
        AnswerView view2 = new AnswerView(2, true, "answer2");

        Answer answer3 = new Answer(3, false, "answer3", new Question());
        AnswerView view3 = new AnswerView(3, false, "answer3");

        return new Object[][]{
                {answer1, view1},
                {answer2, view2},
                {answer3, view3},
        };
    }
}
