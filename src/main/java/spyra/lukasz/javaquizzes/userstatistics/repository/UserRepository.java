package spyra.lukasz.javaquizzes.userstatistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u join fetch u.role join fetch u.takenQuizzes where u.email = ?1")
    Optional<User> findUserByEmail(@NotNull @Email String email);

}
