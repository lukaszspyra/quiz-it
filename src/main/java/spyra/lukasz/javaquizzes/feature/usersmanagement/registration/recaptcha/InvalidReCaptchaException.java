package spyra.lukasz.javaquizzes.feature.usersmanagement.registration.recaptcha;

/**
 * Exception for non-valid reCaptcha
 */
public class InvalidReCaptchaException extends Exception {
    InvalidReCaptchaException(String message) {
        super(message);
    }
}
