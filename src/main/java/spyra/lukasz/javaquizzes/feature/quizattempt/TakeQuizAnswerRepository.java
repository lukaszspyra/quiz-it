package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.TakeQuizAnswer;

interface TakeQuizAnswerRepository extends JpaRepository<TakeQuizAnswer, Long> {
}
