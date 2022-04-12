package spyra.lukasz.javaquizzes.feature.usersmanagement.registration;

class UserAlreadyExistsException extends Exception{
    UserAlreadyExistsException(String message) {
        super(message);
    }
}
