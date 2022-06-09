package spyra.lukasz.javaquizzes.feature.usersmanagement.registration.recaptcha;

public class InvalidReCaptchaException extends Exception {
    InvalidReCaptchaException(String message) {
        super(message);
    }
}
