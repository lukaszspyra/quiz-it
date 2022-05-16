package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

@Mapper(componentModel = "spring")
interface FinalResultMapper {

    @Mapping(target = "attemptTime", expression = "java(takeQuiz.calcAttemptTime())")
    FinalResultView toFinalResultView(TakeQuiz takeQuiz);

}
