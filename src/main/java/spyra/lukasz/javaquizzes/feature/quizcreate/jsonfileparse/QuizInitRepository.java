package spyra.lukasz.javaquizzes.feature.quizcreate.jsonfileparse;

import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.Quiz;

/**
 * Quiz repository for database initialization from both API calls and JSON files
 */
public interface QuizInitRepository extends JpaRepository<Quiz, Long> {
}
