package spyra.lukasz.javaquizzes.feature.quizresults;

import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.TakeQuizAnswer;

import java.util.List;
import java.util.UUID;

interface MarkedAnswerDetailsRepository extends JpaRepository<TakeQuizAnswer, UUID> {

    List<TakeQuizAnswer> findTakeQuizAnswerByQuestionIdAndTakeQuizId(UUID questionId, UUID takeQuizId);
}
