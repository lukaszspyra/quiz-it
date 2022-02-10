package spyra.lukasz.javaquizzes.feature.resultdetails;

import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.TakeQuizAnswer;

import java.util.List;

interface MarkedAnswerDetailsRepository extends JpaRepository<TakeQuizAnswer, Long> {

    List<TakeQuizAnswer> findTakeQuizAnswerByQuestionIdAndTakeQuizId(long questionId, long takeQuizId);
}
