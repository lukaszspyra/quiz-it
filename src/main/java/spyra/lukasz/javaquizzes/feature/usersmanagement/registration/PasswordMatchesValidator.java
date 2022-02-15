package spyra.lukasz.javaquizzes.feature.usersmanagement.registration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, NewUserDTO> {

    @Override
    public boolean isValid(NewUserDTO userDTO, ConstraintValidatorContext context) {
        return Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword());
    }
}
