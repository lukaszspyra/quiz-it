package spyra.lukasz.javaquizzes.feature.resultdetails;

import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

interface ResultDetailsRepository extends JpaRepository<TakeQuiz, Long> {
}
