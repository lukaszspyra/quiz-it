package spyra.lukasz.javaquizzes.feature.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spyra.lukasz.javaquizzes.shared.Quiz;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Starts the demo attempt.
 */
@Component
@RequiredArgsConstructor
class DemoStarter {

    private final DemoService service;

    private final TakeDemoMapper takeDemoMapper;

    /**
     * Begins demo quiz attempt.
     * 
     * Demo is retrieved from database, whilst {@link TakeQuiz} is created and not persisted, due to temporary nature of demo results.
     *
     * @param demoId of demo quiz
     * @return mapped instance of {@link TakeDemoDTO}
     */
    @Transactional
    public TakeDemoDTO takeQuiz(UUID demoId) {
        Quiz presentQuiz = service.getDemoById(demoId);
        TakeQuiz takeQuiz = new TakeQuiz();
        takeQuiz.setQuiz(presentQuiz);
        LocalDateTime now = LocalDateTime.now();
        takeQuiz.setStart(now);
        takeQuiz.setFinish(now);
        return takeDemoMapper.toDTO(takeQuiz);
    }

}
