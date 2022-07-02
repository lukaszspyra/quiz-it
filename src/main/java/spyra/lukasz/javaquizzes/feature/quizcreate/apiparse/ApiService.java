package spyra.lukasz.javaquizzes.feature.quizcreate.apiparse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.feature.quizcreate.jsonfileparse.JsonReader;
import spyra.lukasz.javaquizzes.feature.quizcreate.jsonfileparse.QuizInitRepository;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Gets random {@link Quiz} from API call.
 *
 * @see <a href="https://quizapi.io/">quizapi.io</a>
 */
@Service
class ApiService {

    private final JsonReader jsonReader;

    private final QuizInitRepository repository;

    private final String apiURL;

    private final String apiKey;

    ApiService(JsonReader jsonReader, QuizInitRepository repository, @Value("${api.url}") String apiURL, @Value("${api.key}") String apiKey) {
        this.jsonReader = jsonReader;
        this.repository = repository;
        this.apiURL = apiURL;
        this.apiKey = apiKey;
    }


    /**
     * Gets {@link Quiz} form API call, based on tag and questionNumber chosen by the user.
     *
     * @param tag            Category tag of the quiz
     * @param questionNumber of the quiz
     * @return parsed and saved quiz from database
     * @throws IOException          when {@link java.util.Properties} file is not found
     * @throws InterruptedException when there is API connection issue with sending request
     */
    Quiz getRandomQuizFromApi(final String tag, final String questionNumber) throws IOException, InterruptedException {
        String uri = generateUri(tag, questionNumber);
        final HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(createRequest(uri), HttpResponse.BodyHandlers.ofString());
        final Quiz quizFromApi = jsonReader.parseApiJson(response.body(), tag);
        return repository.save(quizFromApi);
    }

    private String generateUri(final String tag, final String questionNumber) {
        final StringBuilder builder = new StringBuilder();
        return builder.append(apiURL)
                .append(apiKey)
                .append("&limit=")
                .append(questionNumber)
                .append("&tags=")
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
