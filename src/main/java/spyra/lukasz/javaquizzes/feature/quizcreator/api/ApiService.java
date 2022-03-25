package spyra.lukasz.javaquizzes.feature.quizcreator.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.feature.quizcreator.jsonfileparser.JsonReader;
import spyra.lukasz.javaquizzes.feature.quizcreator.jsonfileparser.QuizInitRepository;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Gets random {@link Quiz} from API call.
 * @see <a href="https://quizapi.io/">quizapi.io</a>
 */
@Service
class ApiService {

    private static final String API_URL = "https://quizapi.io/api/v1/questions?apiKey=";

    @Autowired
    private JsonReader jsonReader;

    @Autowired
    private PropertiesReader propertiesReader;

    @Autowired
    private QuizInitRepository repository;

    /**
     * Gets {@link Quiz} form API call, based on tag and difficulty choosen by the user.
     * @param tag Category tag of the quiz
     * @param difficulty of the quiz
     * @return parsed and saved quiz from database
     * @throws IOException when {@link java.util.Properties} file is not found
     * @throws InterruptedException when there is API connection issue with sending request
     */
    Quiz getRandomQuizFromApi(final String tag, final String difficulty) throws IOException, InterruptedException {
        String uri = generateUri(tag, difficulty);
        final HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(createRequest(uri), HttpResponse.BodyHandlers.ofString());
        final Quiz quizFromApi = jsonReader.parseApiJson(response.body());
        return repository.save(quizFromApi);
    }

    private String generateUri(final String tag, final String difficulty) throws IOException {
        final StringBuilder builder = new StringBuilder();
        return builder.append(API_URL)
                .append(propertiesReader.readProperty("api_key", "settings.properties"))
                .append("&")
                .append(propertiesReader.readProperty("questions_number", "settings.properties"))
                .append("&difficulty=")
                .append(difficulty)
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
