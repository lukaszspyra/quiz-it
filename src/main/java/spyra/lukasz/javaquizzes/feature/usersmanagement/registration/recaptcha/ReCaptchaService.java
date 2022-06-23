package spyra.lukasz.javaquizzes.feature.usersmanagement.registration.recaptcha;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.regex.Pattern;

/**
 * Process reCaptcha response
 *
 * Response form view is validated and verified on proper google's url
 */
@Service
@RequiredArgsConstructor
public class ReCaptchaService {

    private final RestOperations restTemplate;

    private final Environment environment;

    private static final Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

    /**
     * Process response from reCaptcha view module
     *
     * Obtain reCaptcha secret and verify url from application.properties to create post url
     *
     * @param response from view as String
     * @throws InvalidReCaptchaException if reCaptcha response is not valid or not verified successfully
     */
    public void processResponse(String response) throws InvalidReCaptchaException {
        if (!validateResponse(response)) {
            throw new InvalidReCaptchaException("ReCaptcha response contains invalid characters");
        }
        final String secret = environment.getProperty("google.recaptcha.key.secret");
        final String verifyURL = environment.getProperty("google.recaptcha.verify.url");

        URI verifyUri = URI.create(String.format(verifyURL + "?secret=%s&response=%s",
                secret, response));

        ReCaptchaResponse reCaptchaResponse = restTemplate.postForEntity(verifyUri, response, ReCaptchaResponse.class).getBody();

        if (reCaptchaResponse == null || !reCaptchaResponse.isSuccess()) {
            throw new InvalidReCaptchaException("reCaptcha was not successfully validated");
        }
    }

    /**
     * Validate reCaptcha view's response
     *
     * Check that the given String is neither null nor of length 0.
     * Check for allowed characters regex from pattern {@link #RESPONSE_PATTERN}
     * @param response as String
     * @return true or false for valid/invalid response validation result
     */
    private boolean validateResponse(String response) {
        return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
    }

}
