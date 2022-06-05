package spyra.lukasz.javaquizzes.feature.demo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spyra.lukasz.javaquizzes.shared.Answer;
import spyra.lukasz.javaquizzes.shared.Question;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;
import spyra.lukasz.javaquizzes.shared.TakeQuizAnswer;

import java.util.List;

@Mapper(componentModel = "spring")
interface TakeDemoMapper {

    @Mapping(target = "remainingQuestions", source = "quiz.questions")
    TakeDemoDTO toDTO(TakeQuiz takeQuiz);

    List<TakeDemoAnswerDTO> toDTO(List<TakeQuizAnswer> takeQuizAnswerList);

    TakeDemoAnswerDTO toDTO(TakeQuizAnswer takeQuizAnswer);

    List<QuestionDTO> toQuestionDTO(List<Question> questions);

    QuestionDTO toQuestionDTO(Question question);

    AnswerDTO toAnswerDTO(Answer answer);

}
