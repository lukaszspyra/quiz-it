package spyra.lukasz.javaquizzes.feature.quizselector;

import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.util.List;

interface QuizSelectRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findQuizzesByRestrictedFalse();

    Quiz findQuizByIdAndRestrictedFalse(long quizId);

    List<Quiz> findQuizzesByRestrictedTrue();

    Quiz findQuizByIdAndRestrictedTrue(long quizId);
}
