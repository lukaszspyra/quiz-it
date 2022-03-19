package spyra.lukasz.javaquizzes.feature.jsonfileparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Quiz;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;

@Component
class DbInitLoaderFromFile {

    private static final Path FILE_PATH = Path.of("src/main/resources/quizzcontent/free/Linux.json");
    public static final String PREDEFINED = "Predefined";

    @Autowired
    private JsonReader jsonReader;

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private QuizInitRepository quizRepo;

    @PostConstruct
    void loadQuizzesFromFile() throws IOException {
        final QuizJson quizJson = jsonReader.readJsonFile(FILE_PATH);
        final Quiz quiz = jsonMapper.toEntity(quizJson);
        quiz.setTitle(PREDEFINED);
        quizRepo.save(quiz);
    }
}
