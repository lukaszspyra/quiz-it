package spyra.lukasz.javaquizzes.feature.usersmanagement.registration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
