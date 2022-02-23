package spyra.lukasz.javaquizzes.feature.usersmanagement.authority;

import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.AvailableRole;

@Component
final class ChangeUserRoleDTO {

    private long id;

    private String name;

    private String email;

    private String role;

}
