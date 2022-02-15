package spyra.lukasz.javaquizzes.feature.usersmanagement.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.User;

import java.util.Optional;

interface UserRegisterRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
