package spyra.lukasz.javaquizzes.feature.usersmanagement.registration;

class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
