package spyra.lukasz.javaquizzes.feature.usersmanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.User;

interface UserManagementRepository extends JpaRepository<User, Long> {
    void deleteById(long idToDelete);
}
