package spyra.lukasz.javaquizzes.feature.quizcreator.jsonfileparser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.io.IOException;
import java.nio.file.Path;

@Component
public class JsonReader {

    @Autowired
    private JsonMapper jsonMapper;

    Quiz readJsonFile(final Path jsonPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        JsonNode jsonNode = mapper.readTree(jsonPath.toFile());
        return jsonMapper.toEntity(mapper.readValue(jsonNode.toString(), QuizJson.class));
    }

}
