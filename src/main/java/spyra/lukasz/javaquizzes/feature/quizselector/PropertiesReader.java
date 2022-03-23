package spyra.lukasz.javaquizzes.feature.quizselector;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@Component
final class PropertiesReader {

    String readProperty(String propertyName) throws IOException {
        return loadProperties("settings.properties").getProperty(propertyName);
    }

    private Properties loadProperties(String fileName) throws IOException {
        String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        String filePath = rootPath + fileName;
        Properties properties = new Properties();
        properties.load(new FileInputStream(filePath));
        return properties;
    }
}
