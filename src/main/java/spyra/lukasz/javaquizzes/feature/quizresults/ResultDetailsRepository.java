package spyra.lukasz.javaquizzes.feature.quizresults;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

import java.util.UUID;

interface ResultDetailsRepository extends JpaRepository<TakeQuiz, UUID> {

    /**
     * Secured to check if {@link TakeQuiz} belongs to currently logged in {@link spyra.lukasz.javaquizzes.shared.User}
     * @param id
     * @return {@link TakeQuiz} for currently logged in User
     */
    @PostAuthorize("returnObject.user.email == authentication.principal.getUsername()")
    TakeQuiz getById(UUID id);
}
