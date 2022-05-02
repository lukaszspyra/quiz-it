package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * Process quiz end
 */
@Component
class Finisher {

    private final TakeQuizRepository takeQuizRepository;

    Finisher(TakeQuizRepository takeQuizRepository) {
        this.takeQuizRepository = takeQuizRepository;
    }

    /**
     * Finish the quiz attempt by saving finish time
     *
     * @param session    for cleanign of questions
     * @param takeQuizId to be ended
     * @return saved finished quiz attempt
     */
    TakeQuiz finishQuizAttempt(HttpSession session, Long takeQuizId) {
        session.removeAttribute("questions");
        TakeQuiz byId = takeQuizRepository.getById(takeQuizId);
        byId.setFinish(LocalDateTime.now());
        return takeQuizRepository.save(byId);
    }

}
