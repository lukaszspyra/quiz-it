package spyra.lukasz.javaquizzes.quizselector;

import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.Quiz;

interface QuizRepository extends JpaRepository<Quiz, Long> {
}
