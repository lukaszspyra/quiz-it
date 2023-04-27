package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import spyra.lukasz.javaquizzes.shared.Question;
import spyra.lukasz.javaquizzes.shared.Quiz;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * Delivers Questions with fetched Answers
 */
interface QuizQuestionsProviderRepository extends JpaRepository<Question, UUID> {

    /**
     * Finds list of Questions by ID,
     * {@link EntityGraph} fetches answers for lazy Questions entities list
     *
     * @param quiz matching found questions
     * @return questions with answers fetched
     */
    @EntityGraph(attributePaths = "answers")
    List<Question> findQuestionsByQuiz(@NotNull Quiz quiz);
}
