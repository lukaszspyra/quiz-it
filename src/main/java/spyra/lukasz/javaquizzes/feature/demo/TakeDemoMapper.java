package spyra.lukasz.javaquizzes.feature.demo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spyra.lukasz.javaquizzes.shared.Answer;
import spyra.lukasz.javaquizzes.shared.Question;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

/**
 * MapStruct interface to deep map {@link TakeQuiz} to {@link TakeDemoDTO}
 */
@Mapper(componentModel = "spring")
interface TakeDemoMapper {

    @Mapping(target = "questionDTOs", source = "quiz.questions")
    TakeDemoDTO toDTO(TakeQuiz takeQuiz);

    @Mapping(source = "questionDTOs", target = "quiz.questions")
    TakeQuiz fromDTO(TakeDemoDTO takeDemoDTO);

    QuestionDTO toQuestionDTO(Question question);

    Question toQuestion(QuestionDTO questionDTO);

    AnswerDTO toAnswerDTO(Answer answer);

    Answer toAnswer(AnswerDTO answerDTO);

}
