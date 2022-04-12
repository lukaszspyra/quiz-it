package spyra.lukasz.javaquizzes.feature.usersmanagement.registration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * Validator for matching passwords during registration of new {@link spyra.lukasz.javaquizzes.shared.User}
 */
class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, NewUserDTO> {

    @Override
    public boolean isValid(NewUserDTO userDTO, ConstraintValidatorContext context) {
        return Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword());
    }
}
