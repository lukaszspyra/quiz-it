package spyra.lukasz.javaquizzes.feature.jsonparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Quiz;

@Component
class JsonMapper {

    @Autowired
    private QuizMapperMapStruct mapper;

    Quiz toEntity(final QuizJson quizJson) {
        return mapper.quizJsonToQuiz(quizJson);
    }

}
