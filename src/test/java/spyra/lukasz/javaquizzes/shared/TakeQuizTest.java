package spyra.lukasz.javaquizzes.shared;

import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEqualsDeep;

public class TakeQuizTest {

private TakeQuiz takeQuizToUpdate = new TakeQuiz();
private TakeQuiz takeQuizExpectedUpdate = new TakeQuiz();

    @Test
    public void shallUpdateQuizAttemptWithScoreAndQuestFinishTime() {
        //given
        LocalDateTime now = LocalDateTime.now();
        int questionScore = 5;
        takeQuizExpectedUpdate.setScore(questionScore);
        takeQuizExpectedUpdate.setFinish(now);

        //when
        final TakeQuiz result = takeQuizToUpdate.progressQuizAttempt(5, now);

        //then
        assertEquals(result.getScore(), takeQuizExpectedUpdate.getScore());
        assertEquals(result.getFinish(), takeQuizExpectedUpdate.getFinish());
    }
}