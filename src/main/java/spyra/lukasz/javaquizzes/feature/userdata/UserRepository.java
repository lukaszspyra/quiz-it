package spyra.lukasz.javaquizzes.feature.userdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spyra.lukasz.javaquizzes.shared.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u join fetch u.role left join fetch u.takenQuizzes where u.email = ?1")
    Optional<User> findUserByEmail(@NotNull @Email String email);

}
