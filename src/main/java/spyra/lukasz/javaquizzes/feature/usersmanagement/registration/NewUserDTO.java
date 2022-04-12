package spyra.lukasz.javaquizzes.feature.usersmanagement.registration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * DTO for new {@link spyra.lukasz.javaquizzes.shared.User} registration.
 *
 * All data is validated by javax validators and custom {@link PasswordMatches} validating annotation
 */
@Component
@Getter
@Setter
@PasswordMatches
class NewUserDTO {

    @NotNull(message = "Name can not be null")
    @NotEmpty(message = "Name can not be empty")
    private String name;

    @NotNull(message = "Email can not be null")
    @NotEmpty(message = "Email can not be empty")
    @Email
    private String email;

    @NotNull(message = "Password can not be null")
    @NotEmpty(message = "Password can not be empty")
    private String password;

    @NotNull(message = "Matching password can not be null")
    @NotEmpty(message = "Matching password can not be empty")
    private String matchingPassword;

}
