package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Component
class Finisher {

    @Autowired
    private TakeQuizRepository takeQuizRepository;

    TakeQuiz finishQuizAttempt(HttpSession session, Long takeQuizId) {
        session.removeAttribute("questions");
        TakeQuiz byId = takeQuizRepository.getById(takeQuizId);
        byId.setFinish(LocalDateTime.now());
        return takeQuizRepository.save(byId);
    }
}
