package spyra.lukasz.javaquizzes.config.healthcheck;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class HealthCheckController {

    private final HealthCheckRepository healthCheckRepository;

    @GetMapping(value = "/healthz")
    public ResponseEntity<HttpStatus> sendViaResponseEntity() {
        ResponseEntity<HttpStatus> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        if (healthCheckRepository.count() == 0) {
            responseEntity = new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
        return responseEntity;
    }
}
