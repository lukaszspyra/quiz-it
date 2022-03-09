package spyra.lukasz.javaquizzes.feature.usersmanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spyra.lukasz.javaquizzes.shared.User;

import javax.validation.constraints.NotNull;
import java.util.Optional;

interface UserManagementRepository extends JpaRepository<User, Long> {

    @Query("select u from User u join fetch u.role left join fetch u.takenQuizzes where u.id = ?1 and u.role.name <> ?2")
    Optional<User> findByIdAndRoleNameNotLike(long id, @NotNull String role_name);
}
