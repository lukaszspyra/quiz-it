package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Process quiz end
 */
@Component
class Finisher {

    @Autowired
    private TakeQuizRepository takeQuizRepository;

    /**
     * Finish the quiz attempt by saving finish time
     * @param session for cleanign of questions
     * @param takeQuizId to be ended
     * @return saved finished quiz attempt
     */
    TakeQuiz finishQuizAttempt(HttpSession session, Long takeQuizId) {
        session.removeAttribute("questions");
        TakeQuiz byId = takeQuizRepository.getById(takeQuizId);
        byId.setFinish(LocalDateTime.now());
        return takeQuizRepository.save(byId);
    }

    /**
     * Calculates total time used for quiz
     * @param takeQuiz given attempt
     * @return difference between start and finish quiz times
     */
    Duration calcAttemptTime(TakeQuiz takeQuiz){
        var start = takeQuiz.getStart();
        var finish = takeQuiz.getFinish();
        return Duration.between(start, finish);
    }
}
