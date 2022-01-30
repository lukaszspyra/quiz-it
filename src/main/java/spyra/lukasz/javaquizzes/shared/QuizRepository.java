package spyra.lukasz.javaquizzes.shared;

import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
