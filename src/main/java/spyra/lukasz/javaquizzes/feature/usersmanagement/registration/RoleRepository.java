package spyra.lukasz.javaquizzes.feature.usersmanagement.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.Role;

interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String roleName);
}
