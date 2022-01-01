package spyra.lukasz.javaquizzes.userstatistics;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(@NotNull @Email String email);

}
