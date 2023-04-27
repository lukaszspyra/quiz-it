package spyra.lukasz.javaquizzes.feature.quizcreate.jsonfileparse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

@Component
@RequiredArgsConstructor
public final class JsonReader {

    private final QuizMapperMapStruct jsonMapper;

    /**
     * Reads JSON file content from InputStream to {@link Quiz} entity.
     *
     * Adds empty API request quiz title tag, as it handles jsons from file.
     *
     * @param resourceInputStream to be parsed from JSON format to quiz contents
     * @return complete entity
     * @throws IOException when resources with content not found
     */
    Quiz readJsonFile(final InputStream resourceInputStream) throws IOException {
        ObjectMapper mapper = createObjectMapperWithTimeModule();
        mapper.setInjectableValues(prepareCategoryTag(""));
        JsonNode jsonNode = mapper.readTree(resourceInputStream);
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
