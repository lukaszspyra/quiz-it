package spyra.lukasz.javaquizzes.feature.quizcreator.jsonfileparser;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Quiz;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
class DbInitLoaderFromFile {

    private static final Path FILES_PATH = Path.of("src/main/resources/quizcontent/free");

    @Autowired
    private JsonReader jsonReader;

    @Autowired
    private QuizInitRepository quizRepo;

    @PostConstruct
    void loadQuizzesFromFile() throws IOException {
        try (Stream<Path> files = Files.list(FILES_PATH)){
            files
                    .map(this::getQuizFromJson)
                    .forEach(quizRepo::save);
        }
    }

    @SneakyThrows
    private Quiz getQuizFromJson(Path file) {
        return jsonReader.readJsonFile(file);
    }
}
