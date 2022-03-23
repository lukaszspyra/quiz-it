package spyra.lukasz.javaquizzes.feature.quizselector;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpRequest;

@Service
class ApiService {

    private HttpRequest createRequest(String uri) {
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
    }

}
