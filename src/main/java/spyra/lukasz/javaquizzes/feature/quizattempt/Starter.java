package spyra.lukasz.javaquizzes.feature.quizattempt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Quiz;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;
import spyra.lukasz.javaquizzes.shared.User;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Starts the quiz attempt.
 */
@Component
@RequiredArgsConstructor
class Starter {

    private final QuizProviderRepository quizProviderRepository;

    private final TakeQuizRepository takeQuizRepository;

    /**
     * Creates quiz attempt for logged in {@link User}.
     * <p>
     * New quiz attempt is created for saved quiz and user, start and finish time is set, complete entity is saved to database.
     *
     * @param quizId    currently taken quiz
     * @param userEmail currently logged in {@link User}
     * @return created quiz attempt
     */
    TakeQuiz takeQuiz(UUID quizId, String userEmail) {
        Quiz presentQuiz = quizProviderRepository.findQuizById(quizId);
        User activeUser = takeQuizRepository.findUserByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        TakeQuiz takeQuiz = new TakeQuiz();
        takeQuiz.setQuiz(presentQuiz);
        LocalDateTime now = LocalDateTime.now();
        takeQuiz.setStart(now);
        takeQuiz.setFinish(now);
        takeQuiz.setUser(activeUser);
        return takeQuizRepository.save(takeQuiz);
    }

}
