package spyra.lukasz.javaquizzes.shared;

import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String roleName);
}
