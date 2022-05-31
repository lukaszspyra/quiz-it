package spyra.lukasz.javaquizzes.feature.demo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

@Mapper(componentModel = "spring")
interface DemoResultMapper {

    @Mapping(target = "attemptTime", expression = "java(takeQuiz.calcAttemptTime())")
    FinalResultView toFinalResultView(TakeQuiz takeQuiz);

}
