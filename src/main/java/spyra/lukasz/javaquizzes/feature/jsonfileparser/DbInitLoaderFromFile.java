package spyra.lukasz.javaquizzes.feature.jsonfileparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Component
class DbInitLoaderFromFile {

    private static final Path FILE_PATH = Path.of("src/main/resources/quizzcontent/free/Linux.json");

    @Autowired
    private JsonReader jsonReader;

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private QuizInitRepository quizRepo;

    @PostConstruct
    void loadQuizzesFromFile() throws IOException {
        List<QuizJson> quizJsons = jsonReader.readJsonFile(FILE_PATH);
        quizJsons.stream()
                .map(jsonMapper::toEntity)
                .forEach(e -> quizRepo.save(e));
    }
}
