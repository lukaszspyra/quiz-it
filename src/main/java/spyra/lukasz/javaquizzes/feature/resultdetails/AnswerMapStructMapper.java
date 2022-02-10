package spyra.lukasz.javaquizzes.feature.resultdetails;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spyra.lukasz.javaquizzes.shared.Answer;
import spyra.lukasz.javaquizzes.shared.TakeQuizAnswer;

import java.util.List;

@Mapper(componentModel = "spring")
interface AnswerMapStructMapper {

    List<AnswerDetailsView> answersEntityToDetailsViews(List<Answer> answers);

    List<Answer> detailsViewsToEntities(List<AnswerDetailsView> answerDetailsViews);

    @Mapping(source = "answer.id", target = "id")
    @Mapping(source = "answer.correct", target = "correct")
    @Mapping(source = "answer.content", target = "content")
    List<AnswerDetailsView> markedAnsEntityToDetailsView(List<TakeQuizAnswer> markedAnswers);
}
