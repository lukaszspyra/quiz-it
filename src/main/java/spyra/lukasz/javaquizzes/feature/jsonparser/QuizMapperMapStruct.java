package spyra.lukasz.javaquizzes.feature.jsonparser;

import org.mapstruct.Mapper;
import spyra.lukasz.javaquizzes.shared.Answer;
import spyra.lukasz.javaquizzes.shared.Question;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.util.List;

@Mapper
public interface QuizMapperMapStruct {

    Quiz quizJsonToQuiz(QuizJson quizJson);

    List<Question> questionJsonToQuestion(List<QuestionJson> questionsJson);

    List<Answer> answerJsonToAnswer(List<AnswerJson> answersJson);
}
