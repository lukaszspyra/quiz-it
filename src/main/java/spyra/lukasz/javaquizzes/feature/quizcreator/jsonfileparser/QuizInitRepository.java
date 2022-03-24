package spyra.lukasz.javaquizzes.feature.quizcreator.jsonfileparser;

import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.Quiz;

interface QuizInitRepository extends JpaRepository<Quiz, Long> {
}
