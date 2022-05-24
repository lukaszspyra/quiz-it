package spyra.lukasz.javaquizzes.feature.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls progress during Demo quiz attempt.
 *
 * As it serves for demo purpose, results are not saved in database, only {@link TakeQuiz} instance is updated.
 */
@Component
@RequiredArgsConstructor
class DemoProgresser {

    private final ScoreCounter scoreCounter;

    /**
     * Make progress of demo quiz attempt by updating {@link TakeQuiz} with given list of {@link Answer}
     *
     * @param question    during attempt
     * @param markedIds   selected answers Ids
     * @param currentDemo current {@link TakeQuiz} attempt instance
     */
    void progressQuiz(Question question, List<Long> markedIds, TakeQuiz currentDemo) {
        final List<Answer> answered = question.selectAnswers(markedIds);
        final TakeQuiz updatedDemo = currentDemo.progressQuizAttempt(scoreCounter.count(answered), LocalDateTime.now());
        updatedDemo.setGivenAnswers(updateGivenAnswers(answered, question, updatedDemo));
    }

    /**
     * Creates new complete list of {@link TakeQuizAnswer}
     *
     * @param answered    marked answers by the user
     * @param question    current demo question
     * @param updatedQuiz current demo quiz attempt
     * @return list of created {@link TakeQuizAnswer}
     */
    private List<TakeQuizAnswer> updateGivenAnswers(List<Answer> answered, Question question, TakeQuiz updatedQuiz) {
        List<TakeQuizAnswer> takeQuizAnswers = new ArrayList<>();
        for (var answer : answered) {
            takeQuizAnswers.add(new TakeQuizAnswer(updatedQuiz, question, answer));
        }
        return takeQuizAnswers;
    }

}
