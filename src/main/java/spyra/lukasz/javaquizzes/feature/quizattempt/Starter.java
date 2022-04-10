package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Quiz;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;
import spyra.lukasz.javaquizzes.shared.User;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Starts the quiz attempt.
 */
@Component
class Starter {

    @Autowired
    private QuizProviderRepository quizProviderRepository;

    @Autowired
    private TakeQuizRepository takeQuizRepository;

    /**
     * Creates quiz attempt for logged in {@link User}.
     *
     * New quiz attempt is created for saved quiz and user, start and finish time is set, complete entity is saved to database.
     * @param quizId currently taken quiz
     * @param userEmail currently logged in {@link User}
     * @return created quiz attempt
     */
    TakeQuiz takeQuiz(long quizId, String userEmail) {
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

    /**
     * Calculates latest available quiz end time moment in epoch second
     * @param takeQuiz current started quiz attempt
     * @param minutesForWholeQuiz minutes available for the quiz attempt
     * @return time moment in Java epoch, when the quiz must end
     */
    long calcTimeForQuizInEpochSeconds(TakeQuiz takeQuiz, int minutesForWholeQuiz) {
        return takeQuiz.getStart().atZone(ZoneId.systemDefault()).plusMinutes(minutesForWholeQuiz).toInstant().getEpochSecond();
    }
}
