package spyra.lukasz.javaquizzes.feature.jsonparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.QuizRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Component
class DbInitLoaderFromFile {

    private static final Path filePath = Path.of("src/main/resources/quizzescontent/quizdatabase.json");

    @Autowired
    private JsonReader jsonReader;

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private QuizRepository quizRepo;

    @PostConstruct
    void loadQuizzesFromFile() throws IOException {
        List<QuizJson> quizJsons = jsonReader.readJsonFile(filePath);
        quizJsons.stream()
                .map(jsonMapper::toEntity)
                .forEach(e -> quizRepo.save(e));
    }
}