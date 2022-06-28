package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * Reads {@link Properties} and returns its value
 */
@Component
final class QuestionTimeReader {

    /**
     * Reads given {@link Properties} from given file.
     *
     * @param propertyName key in Properties file
     * @param filePath     path of given file.properties
     * @return String value of the property key
     * @throws IOException when Property file is not found
     */
    String readProperty(String propertyName, String filePath) throws IOException {
        return loadProperties(filePath).getProperty(propertyName);
    }

    private Properties loadProperties(String filePath) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(filePath));
        return properties;
    }
}
