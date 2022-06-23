package spyra.lukasz.javaquizzes.feature.usersmanagement.registration.recaptcha;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * ResponseType model for reCaptcha {@link org.springframework.web.client.RestOperations#postForEntity(URI, Object, Class)}
 *
 * Contains data returned from verification server
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "success",
        "challenge_ts",
        "hostname",
        "error-codes"
})
@Getter
@Setter
public class ReCaptchaResponse {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("challenge_ts")
    private String challengeTs;

    @JsonProperty("hostname")
    private String hostname;

    @JsonProperty("error-codes")
    private ErrorCode[] errorCodes;

    @JsonIgnore
    public boolean hasClientError() {
        if(errorCodes == null) {
            return false;
        }
        for(ErrorCode error : errorCodes) {
            switch(error) {
                case InvalidResponse:
                case MissingResponse:
                case BadRequest:
                case TimeOut:
                    return true;
            }
        }
        return false;
    }

    /**
     * Possible values of ErrorCodes in reCaptcha verification result model
     */
    enum ErrorCode {
        MissingSecret,
        InvalidSecret,
        MissingResponse,
        InvalidResponse,
        BadRequest,
        TimeOut;

        private static final Map<String, ErrorCode> errorsMap = new HashMap<>(6);

        static {
            errorsMap.put("missing-input-secret",   MissingSecret);
            errorsMap.put("invalid-input-secret",   InvalidSecret);
            errorsMap.put("missing-input-response", MissingResponse);
            errorsMap.put("invalid-input-response", InvalidResponse);
            errorsMap.put("bad-request", BadRequest);
            errorsMap.put("timeout-or-duplicate", TimeOut);
        }

        @JsonCreator
        public static ErrorCode createForValue(String value) {
            return errorsMap.get(value.toLowerCase());
        }
    }
}
