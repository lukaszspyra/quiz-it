package spyra.lukasz.javaquizzes.feature.usersmanagement.authority;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.AvailableRole;

@Component
@Getter
@Setter
final class ChangeUserRoleDTO {

    private long id;

    private String name;

    private String email;

    private String role;

}
