package spyra.lukasz.javaquizzes.feature.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spyra.lukasz.javaquizzes.shared.Quiz;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * Quiz repository used to fetch demo quizzes
 */
interface DemoRepository extends JpaRepository<Quiz, UUID> {

    List<Quiz> findAllByDemoTrueAndTitleEqualsIgnoreCase(@NotNull String title);

    /**
     * Gets demo quiz entity with fetched questions
     * @param id of demo to be found
     * @return {@link Quiz} entity with demo == true
     */
    @Query("select q from Quiz q join fetch q.questions where q.id = ?1 and q.demo = true")
    Quiz getDemoWithQuestionsById(@NotNull UUID id);

}
