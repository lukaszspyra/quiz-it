package spyra.lukasz.javaquizzes.feature.quizattempt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

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
    FinalResultView finishQuizAttempt(HttpSession session, Long takeQuizId) {
        session.removeAttribute("questions");
        TakeQuiz byId = takeQuizRepository.getById(takeQuizId);
        byId.setFinish(LocalDateTime.now());
        return mapper.toFinalResultView(takeQuizRepository.save(byId));
    }

}
