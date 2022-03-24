package spyra.lukasz.javaquizzes.feature.quizcreator.jsonfileparser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

@Component
public class JsonReader {

    QuizJson readJsonFile(final Path jsonPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        JsonNode jsonNode = mapper.readTree(jsonPath.toFile());
        return mapper.readValue(jsonNode.toString(), QuizJson.class);
    }

}
