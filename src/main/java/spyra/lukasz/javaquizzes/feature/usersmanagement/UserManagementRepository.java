package spyra.lukasz.javaquizzes.feature.usersmanagement;


import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.User;

import java.util.Optional;

interface UserManagementRepository extends JpaRepository<User, Long> {
    Optional<User> deleteById(long idToDelete);
}
