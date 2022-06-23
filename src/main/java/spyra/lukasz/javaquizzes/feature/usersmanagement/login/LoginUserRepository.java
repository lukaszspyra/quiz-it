package spyra.lukasz.javaquizzes.feature.usersmanagement.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spyra.lukasz.javaquizzes.shared.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Optional;

interface LoginUserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u join fetch u.role where u.email = ?1")
    Optional<User> loginUserByEmail(@NotNull @Email String email);

}
