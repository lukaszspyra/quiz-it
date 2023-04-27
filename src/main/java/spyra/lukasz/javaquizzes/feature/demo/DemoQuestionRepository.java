package spyra.lukasz.javaquizzes.feature.demo;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spyra.lukasz.javaquizzes.shared.Question;
import spyra.lukasz.javaquizzes.shared.Quiz;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * Used to fetch demo questions with possible answers
 */
interface DemoQuestionRepository extends JpaRepository<Question, UUID> {

    /**
     * Gets demo question entities with fetched answers
     *
     * @param quiz of demo to be found
     * @return {@link Quiz} entity with demo == true
     */
    @Query("select q from Question q where q.quiz = ?1")
    @EntityGraph(attributePaths = "answers")
    List<Question> findDemoQuestionsByQuiz(@NotNull Quiz quiz);

}
