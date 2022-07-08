package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;
import spyra.lukasz.javaquizzes.shared.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

interface TakeQuizRepository extends JpaRepository<TakeQuiz, UUID> {

    /**
     * Finds user by email, joining with entities of role and taken quizzes
     * @param email registered user email
     * @return {@link User} if registered, otherwise empty {@link Optional}
     */
    @Query("select u from User u join fetch u.role left join fetch u.takenQuizzes where u.email = ?1")
    Optional<User> findUserByEmail(@NotNull @Email String email);

}
