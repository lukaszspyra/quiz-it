package spyra.lukasz.javaquizzes.feature.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * Handles end of Demo quiz, by saving completion data (time) and cleaning up session attributes
 */
@Component
@RequiredArgsConstructor
final class DemoFinisher {

    TakeDemoDTO finishQuizAttempt(HttpSession session) {
        final TakeDemoDTO demoDTO = (TakeDemoDTO) session.getAttribute("demo");
        cleanUpSession(session);
        demoDTO.setFinish(LocalDateTime.now());
        return demoDTO;
    }

    private void cleanUpSession(HttpSession session) {
        session.removeAttribute("question");
        session.removeAttribute("demo");
    }

}
