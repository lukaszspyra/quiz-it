package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.util.UUID;

/**
 * Delivers Quiz entity with questions
 */
interface QuizProviderRepository extends JpaRepository<Quiz, UUID> {

    /**
     * Finds quiz by ID, checking for restricted object, which can be accessed only by admin roles. @EntityGraph fetches
     * questions for lazy Quiz entity
     * @param id of the quiz to be found
     * @return authorized quiz with questions fetched
     */
    @PostAuthorize("returnObject.restricted == true ? hasAnyAuthority('ADMIN', 'SUPER_ADMIN') : true")
    @EntityGraph(attributePaths = "questions")
    Quiz findQuizById(UUID id);
}
