package spyra.lukasz.javaquizzes.quizselector;

import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
