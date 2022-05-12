package spyra.lukasz.javaquizzes.feature.usersmanagement.registration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for new {@link spyra.lukasz.javaquizzes.shared.User} registration.
 * <p>
 * All data is validated by javax validators and custom {@link PasswordMatches} validating annotation
 */
@Component
@Getter
@Setter
@PasswordMatches
class NewUserDTO {

    @NotNull(message = "Name can not be null")
    @NotEmpty(message = "Name can not be empty")
    @Size(min = 2, max = 70, message = "Name must be between 2 and 70 characters")
    private String name;

    @NotNull(message = "Email can not be null")
    @NotEmpty(message = "Email can not be empty")
    @Email
    @Size(min = 7, max = 50, message = "Email must be between 7 and 50 characters")
    private String email;

    @NotNull(message = "Password can not be null")
    @NotEmpty(message = "Password can not be empty")
    @Size(min = 3, max = 50, message = "Password must be between 3 and 50 characters")
    private String password;

    @NotNull(message = "Matching password can not be null")
    @NotEmpty(message = "Matching password can not be empty")
    @Size(min = 3, max = 50, message = "Password must be between 3 and 50 characters")
    private String matchingPassword;

}
