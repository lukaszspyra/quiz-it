package spyra.lukasz.javaquizzes.feature.usersmanagement.registration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="User already exists")
class UserAlreadyExistsException extends Exception{
    UserAlreadyExistsException(String message) {
        super(message);
    }
}
