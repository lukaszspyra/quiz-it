package spyra.lukasz.javaquizzes.feature.quizcreate.jsonfileparse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.io.IOException;
import java.nio.file.Path;

@Component
@RequiredArgsConstructor
public final class JsonReader {

    private final QuizMapperMapStruct jsonMapper;

    /**
     * Reads JSON file content to {@link Quiz} entity
     *
     * Adds empty API request quiz title tag, as it handles jsons from file.
     *
     * @param jsonPath to JSON file with quiz contents
     * @return complete entity
     * @throws IOException when file with content not found
     */
    Quiz readJsonFile(final Path jsonPath) throws IOException {
        ObjectMapper mapper = createObjectMapperWithTimeModule();
        mapper.setInjectableValues(prepareCategoryTag(""));
        JsonNode jsonNode = mapper.readTree(jsonPath.toFile());
        return jsonMapper.quizJsonToQuiz(mapper.readValue(jsonNode.toString(), QuizJson.class));
    }

    /**
     * Parses JSON String to {@link Quiz} entity
     *
     * @param json formatted string
     * @param titleTag API request tag describing quiz title
     * @return complete Quiz entity
     * @throws JsonProcessingException thrown during Jackson's readTree()
     */
    public Quiz parseApiJson(final String json, final String titleTag) throws JsonProcessingException {
        final ObjectMapper mapper = createObjectMapperWithTimeModule();
        mapper.setInjectableValues(prepareCategoryTag(titleTag));
        return jsonMapper.quizJsonToQuiz(mapper.readValue(mapper.readTree(json).toString(), QuizJson.class));
    }

    private InjectableValues.Std prepareCategoryTag(String tagType) {
        return new InjectableValues.Std()
                .addValue("tag", tagType);
    }

    private ObjectMapper createObjectMapperWithTimeModule() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

}
