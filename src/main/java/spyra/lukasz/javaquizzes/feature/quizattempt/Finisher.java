package spyra.lukasz.javaquizzes.feature.quizattempt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Process quiz end
 */
@Component
@RequiredArgsConstructor
class Finisher {

    private final TakeQuizRepository takeQuizRepository;
    private final FinalResultMapper mapper;


    /**
     * Finish the quiz attempt by saving finish time
     *
     * @param session    for cleaning of questions
     * @param takeQuizId to be ended
     * @return saved finished quiz attempt mapped to view
     */
    FinalResultView finishQuizAttempt(HttpSession session, UUID takeQuizId) {
        cleanUpSession(session);
        TakeQuiz byId = takeQuizRepository.getReferenceById(takeQuizId);
        byId.setFinish(LocalDateTime.now());
        return mapper.toFinalResultView(takeQuizRepository.save(byId));
    }

    private void cleanUpSession(HttpSession session) {
        session.removeAttribute("questions");
        session.removeAttribute("question_timer");
        session.removeAttribute("attempt_timer");
    }

}
