package spyra.lukasz.javaquizzes.feature.usersmanagement;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * DTO to send User details to SUPER_ADMIN user-management view
 */
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
final class ChangeUserRoleDTO {

    private long id;

    private String name;

    private String email;

    private String role;

}
