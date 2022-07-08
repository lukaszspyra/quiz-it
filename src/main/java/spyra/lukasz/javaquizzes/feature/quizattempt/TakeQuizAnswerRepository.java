package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.TakeQuizAnswer;

import java.util.UUID;

interface TakeQuizAnswerRepository extends JpaRepository<TakeQuizAnswer, UUID> {
}
