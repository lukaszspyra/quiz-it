package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.Question;

import java.util.UUID;

interface QuestionRepository extends JpaRepository<Question, UUID> {
}
