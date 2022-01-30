package spyra.lukasz.javaquizzes.feature.jsonparser;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Quiz;

@Component
class JsonMapper {

    Quiz toEntity(QuizJson quizJson) {
        ModelMapper modelMapper = new ModelMapper();
        Quiz quiz = modelMapper.typeMap(QuizJson.class, Quiz.class)
                .addMappings(mapper -> mapper.map(QuizJson::getQuestions, Quiz::setQuestions)).map(quizJson);
        return quiz;
    }
}
