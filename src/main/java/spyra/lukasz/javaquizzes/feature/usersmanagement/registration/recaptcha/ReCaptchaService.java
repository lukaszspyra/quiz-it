package spyra.lukasz.javaquizzes.feature.usersmanagement.registration.recaptcha;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ReCaptchaService {

    private final RestOperations restTemplate;

    private final Environment environment;

    private static final Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

    public void processResponse(String response) throws InvalidReCaptchaException {
        if (!validateResponse(response)) {
            throw new IllegalArgumentException("Response contains invalid characters");
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

    private boolean validateResponse(String response) {
        return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
    }

}
