package spyra.lukasz.javaquizzes.feature.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Quiz;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

import java.time.LocalDateTime;

/**
 * Starts the quiz attempt.
 */
@Component
@RequiredArgsConstructor
class DemoStarter {

    private final DemoService service;

    /**
     * Begins demo quiz attempt
     * <p>
     * Demo is chosen from database, whilst {@link TakeQuiz} is created and not persisted, due to temporary nature of demo results
     *
     * @param demoId of demo quiz
     * @return instance of {@link TakeQuiz}
     */
    TakeQuiz takeQuiz(long demoId) {
        Quiz presentQuiz = service.getDemoById(demoId);
        TakeQuiz takeQuiz = new TakeQuiz();
        takeQuiz.setQuiz(presentQuiz);
        LocalDateTime now = LocalDateTime.now();
        takeQuiz.setStart(now);
        takeQuiz.setFinish(now);
        return takeQuiz;
    }

}
