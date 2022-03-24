package spyra.lukasz.javaquizzes.feature.quizcreator.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
class ApiService {

    private static final String API_URL = "https://quizapi.io/api/v1/questions?apiKey=";

    @Autowired
    private PropertiesReader propertiesReader;

    String getRandomQuizFromApi(final String tag, final String difficulty) throws IOException, InterruptedException {
        String uri = generateUri(tag, difficulty);
        final HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(createRequest(uri), HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private String generateUri(final String tag, final String difficulty) throws IOException {
        final StringBuilder builder = new StringBuilder();
        return builder.append(API_URL)
                .append(propertiesReader.readProperty("api_key", "settings.properties"))
                .append("&")
                .append(propertiesReader.readProperty("questions_number", "settings.properties"))
                .append("&difficulty=")
                .append(difficulty)
                .append("t&ags=")
                .append(tag)
                .toString();
    }

    private HttpRequest createRequest(final String uri) {
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
    }

}
