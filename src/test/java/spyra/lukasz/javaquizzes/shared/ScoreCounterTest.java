package spyra.lukasz.javaquizzes.shared;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ScoreCounterTest {

    private final ScoreCounter counter = new ScoreCounter();
    private final Answer trueAnswer = new Answer();
    private final Answer falseAnswer = new Answer();

    {
        trueAnswer.setCorrect(true);
        falseAnswer.setCorrect(false);
    }

    @Test
    public void shallCountCorrectScoreForGivenAnswers() {
        //given
        List<Answer> answersScore1 = List.of(trueAnswer, falseAnswer, trueAnswer);
        List<Answer> answersScore3 = List.of(trueAnswer, falseAnswer, trueAnswer, trueAnswer, trueAnswer);
        List<Answer> answersScoreNeg4 = List.of(trueAnswer, falseAnswer, falseAnswer, falseAnswer, falseAnswer, falseAnswer);
        List<Answer> answersScore0 = List.of(trueAnswer, falseAnswer, falseAnswer, trueAnswer);
        SoftAssert softAssert = new SoftAssert();

        //when
        int result1 = counter.count(answersScore1);
        int result3 = counter.count(answersScore3);
        int resultNeg4 = counter.count(answersScoreNeg4);
        int result0 = counter.count(answersScore0);

        //then
        softAssert.assertEquals(result1, 1);
        softAssert.assertEquals(result3, 3);
        softAssert.assertEquals(resultNeg4, -4);
        softAssert.assertEquals(result0, 0);
        softAssert.assertAll("Score counter shall give +1 for correct answer, whilst -1 for false answer.");
    }
}