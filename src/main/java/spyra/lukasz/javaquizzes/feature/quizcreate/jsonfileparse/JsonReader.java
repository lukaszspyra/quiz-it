package spyra.lukasz.javaquizzes.feature.quizcreate.jsonfileparse;

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
public final class JsonReader {

    private final QuizMapperMapStruct jsonMapper;

    @Autowired
    JsonReader(final QuizMapperMapStruct jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    /**
     * Reads JSON file content to {@link Quiz} entity
     *
     * @param jsonPath to JSON file with quiz contents
     * @return complete entity
     * @throws IOException when file with content not found
     */
    Quiz readJsonFile(final Path jsonPath) throws IOException {
        ObjectMapper mapper = createObjectMapperWithTimeModule();
        JsonNode jsonNode = mapper.readTree(jsonPath.toFile());
        return jsonMapper.quizJsonToQuiz(mapper.readValue(jsonNode.toString(), QuizJson.class));
    }

    private ObjectMapper createObjectMapperWithTimeModule() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    /**
     * Parses JSON String to {@link Quiz} entity
     *
     * @param json formatted string
     * @return complete Quiz entity
     * @throws JsonProcessingException thrown during Jackson's readTree()
     */
    public Quiz parseApiJson(final String json) throws JsonProcessingException {
        final ObjectMapper mapper = createObjectMapperWithTimeModule();
        return jsonMapper.quizJsonToQuiz(mapper.readValue(mapper.readTree(json).toString(), QuizJson.class));
    }

}
