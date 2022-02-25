package spyra.lukasz.javaquizzes.feature.usersmanagement;


import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.User;

import java.util.Optional;

interface UserAuthorityRepository extends JpaRepository<User, Long> {

    Optional<User> deleteUserById(long idToDelete);
}
