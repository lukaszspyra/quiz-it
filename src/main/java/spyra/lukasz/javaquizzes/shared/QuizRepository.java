package spyra.lukasz.javaquizzes.shared;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findQuizzesByRestrictedFalse();

    Quiz findQuizByIdAndRestrictedFalse(long quizId);

    List<Quiz> findQuizzesByRestrictedTrue();

    Quiz findQuizByIdAndRestrictedTrue(long quizId);
}