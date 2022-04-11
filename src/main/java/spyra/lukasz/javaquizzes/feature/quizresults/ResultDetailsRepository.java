package spyra.lukasz.javaquizzes.feature.quizresults;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

interface ResultDetailsRepository extends JpaRepository<TakeQuiz, Long> {

    @PostAuthorize("returnObject.user.email == authentication.principal.getUsername()")
    TakeQuiz getById(long id);
}
