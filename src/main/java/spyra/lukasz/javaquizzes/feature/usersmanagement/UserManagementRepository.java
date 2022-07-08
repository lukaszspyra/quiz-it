package spyra.lukasz.javaquizzes.feature.usersmanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import spyra.lukasz.javaquizzes.shared.User;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * CRUD operations on {@link User} entity
 */
interface UserManagementRepository extends JpaRepository<User, Long> {

    @Query("select u from User u join fetch u.role left join fetch u.takenQuizzes where u.id = ?1 and u.role.name <> ?2")
    Optional<User> findByIdAndRoleNameNotLike(long id, @NotNull String role_name);

    /**
     * Deletes users by id, only if {@link spyra.lukasz.javaquizzes.shared.Role} is {@link spyra.lukasz.javaquizzes.shared.AvailableRole#SUPER_ADMIN}
     * @param id fo entity to be deleted
     */
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    void deleteById(long id);
}
