package spyra.lukasz.javaquizzes.feature.quizcreator.jsonfileparser;

import com.fasterxml.jackson.core.JsonProcessingException;
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
        ObjectMapper mapper = createObjectMapperWithTimeModule();

        JsonNode jsonNode = mapper.readTree(jsonPath.toFile());
        return jsonMapper.toEntity(mapper.readValue(jsonNode.toString(), QuizJson.class));
    }

    private ObjectMapper createObjectMapperWithTimeModule() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    public Quiz parseApiJson(final String json) throws JsonProcessingException {
        final ObjectMapper mapper = createObjectMapperWithTimeModule();
        return jsonMapper.toEntity(mapper.readValue(mapper.readTree(json).toString(), QuizJson.class));
    }

}
