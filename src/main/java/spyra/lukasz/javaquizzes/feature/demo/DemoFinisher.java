package spyra.lukasz.javaquizzes.feature.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * Handles end of Demo quiz, by saving completion data (time) and cleaning up session attributes
 */
@Component
@RequiredArgsConstructor
final class DemoFinisher {

    private final FinalResultMapper mapper;

    FinalResultView finishQuizAttempt(HttpSession session) {
        final TakeQuiz demo = (TakeQuiz) session.getAttribute("demo");
        cleanUpSession(session);
        demo.setFinish(LocalDateTime.now());
        return mapper.toFinalResultView(demo);
    }

    private void cleanUpSession(HttpSession session) {
        session.removeAttribute("questions");
        session.removeAttribute("demo");
    }

}
