package spyra.lukasz.javaquizzes.feature.quizcreator.api;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * Reads {@link Properties} and returns its value
 */
@Component
final class PropertiesReader {

    /**
     * Reads given {@link Properties} from given file.
     *
     * @param propertyName key in Properties file
     * @param fileName     name of given file.properties
     * @return String value of the property key
     * @throws IOException when Property file is not found
     */
    String readProperty(String propertyName, String fileName) throws IOException {
        return loadProperties(fileName).getProperty(propertyName);
    }

    private Properties loadProperties(String fileName) throws IOException {
        String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        String filePath = rootPath + fileName;
        Properties properties = new Properties();
        properties.load(new FileInputStream(filePath));
        return properties;
    }
}
