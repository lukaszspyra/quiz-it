package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;
import spyra.lukasz.javaquizzes.shared.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Optional;

interface TakeQuizRepository extends JpaRepository<TakeQuiz, Long> {

    @Query("select u from User u join fetch u.role left join fetch u.takenQuizzes where u.email = ?1")
    Optional<User> findUserByEmail(@NotNull @Email String email);

}
