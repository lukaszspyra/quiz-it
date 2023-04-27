package spyra.lukasz.javaquizzes.feature.quizcreate.jsonfileparse;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import spyra.lukasz.javaquizzes.shared.Answer;
import spyra.lukasz.javaquizzes.shared.Question;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.util.List;
import java.util.stream.Collectors;

/**
 * MapStruct registered Mapper
 * <p>
 * Used by Spring as bean
 */
@Mapper(componentModel = "spring")
abstract class QuizMapperMapStruct {

    /**
     * MapStruct implementation of mapping from {@link QuizJson} to {@link Quiz}
     *
     * @param quizJson
     * @return Quiz entity without questions set
     */
    abstract Quiz quizJsonToQuiz(final QuizJson quizJson);

    /**
     * MapStruct implementation of mapping from list of {@link QuestionJson} to {@link Question} list
     *
     * @param questionsJson
     * @return list of Question entities without Answers set
     */
    abstract List<Question> questionJsonToQuestion(final List<QuestionJson> questionsJson);

    /**
     * MapStruct implementation of mapping from list of {@link AnswerJson} to {@link Answer} list
     *
     * @param answersJson
     * @return list of Answer entities
     */
    abstract List<Answer> answerJsonToAnswer(final List<AnswerJson> answersJson);

    /**
     * After mapping binding of Questions to Quiz
     * <p>
     * Initial mapping by MapStruct gives Quiz entities without Questions set, whilst Questions do not have Answers set.
     * Those links are created after mapping process.
     *
     * @param quiz with not questions set
     */
    @AfterMapping
    protected void bindQuestionsToQuiz(@MappingTarget Quiz quiz) {
        List<Question> questNotBound = quiz.getQuestions();
        List<Question> questBound = questNotBound.stream()
                .peek(q -> q.setQuiz(quiz))
                .map(this::bindAnswerToQuestion)
                .collect(Collectors.toUnmodifiableList());
        quiz.setQuestions(questBound);
    }

    private Question bindAnswerToQuestion(final Question question) {
        question.getAnswers().forEach(a -> a.setQuestion(question));
        return question;
    }
}
