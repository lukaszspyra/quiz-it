package spyra.lukasz.javaquizzes.feature.quizcreate.jsonfileparse;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;

/**
 * Loads {@link Quiz} from JSON file for database initialization
 */
@Component
@RequiredArgsConstructor
class DbInitLoaderFromFile {
    public static final String CLASSPATH_RESOURCES_QUIZ_FILES = "classpath*:quizcontent/**";

    private final JsonReader jsonReader;

    private final QuizInitRepository quizRepo;

    private final ResourceLoader loader;


    /**
     * Called upon bean init completion, loads resources from all files in given {@link DbInitLoaderFromFile#CLASSPATH_RESOURCES_QUIZ_FILES},
     * converts .json to {@link File} and saves in database
     *
     * @throws IOException when error reading from given file
     * @see Object#getClass() and its {@link Class#getResourceAsStream(String)} for alternative api to read resources from Executable Jar
     */
    void loadQuizzesFromFile() throws IOException {
        Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(loader).getResources(CLASSPATH_RESOURCES_QUIZ_FILES);
        Arrays.stream(resources)
                .filter(e -> Objects.requireNonNull(e.getFilename()).endsWith(".json"))
                .map(this::resourceToInputStream)
                .map(this::parseQuizFromJson)
                .forEach(quizRepo::save);

    }

    /**
     * Wrapper for Spring's {@link Resource#getFile()} for {@link SneakyThrows}
     *
     * @param resource
     * @return file object from Spring's Resource
     */
    @SneakyThrows
    private InputStream resourceToInputStream(Resource resource) {
        return resource.getInputStream();
    }

    /**
     * Reads quizzes from given file path, uses @SneakyThrows for clear code in FP streams
     *
     * @param in InputStream to be parsed
     * @return Quiz entity
     */
    @SneakyThrows
    private Quiz parseQuizFromJson(InputStream in) {
        return jsonReader.readJsonFile(in);
    }
}
