package spyra.lukasz.javaquizzes.feature.jsonfileparser;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
class DbInitLoaderFromFile {

    private static final Path FILES_PATH = Path.of("src/main/resources/quizzcontent/free");
    public static final String PREDEFINED = "Predefined";

    @Autowired
    private JsonReader jsonReader;

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private QuizInitRepository quizRepo;

    @PostConstruct
    void loadQuizzesFromFile() throws IOException {
        try (Stream<Path> files = Files.list(FILES_PATH)){
            files
                    .map(this::getQuizFromJson)
                    .map(json -> jsonMapper.toEntity(json))
                    .forEach(quizRepo::save);
        }
    }

    @SneakyThrows
    private QuizJson getQuizFromJson(Path file) {
        return jsonReader.readJsonFile(file);
    }
}
