package spyra.lukasz.javaquizzes.feature.quizresults.delete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

/**
 * Deletes {@link TakeQuiz} from logged-in {@link spyra.lukasz.javaquizzes.shared.User}
 */
interface ResultDeleteRepository extends JpaRepository<TakeQuiz, Long> {

    /**
     * Secured to check if {@link TakeQuiz} belongs to currently logged-in {@link spyra.lukasz.javaquizzes.shared.User}
     *
     * @param attempt entity to be deleted
     */
    @PreAuthorize("#attempt.getUser().getEmail() == authentication.principal.getUsername()")
    void delete(@NonNull TakeQuiz attempt);

}
