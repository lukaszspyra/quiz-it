package spyra.lukasz.javaquizzes.feature.quizselect;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.util.List;

/**
 * Repository to find quizzes by authorized user's defined parameters
 */
interface QuizSelectRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findQuizzesByRestrictedFalseAndPredefinedTrueAndDemoOrderByTitleAsc(boolean demo);

    List<Quiz> findQuizzesByRestrictedTrueOrderByTitleAsc();

    /**
     * Gets quiz by id and check if {@link spyra.lukasz.javaquizzes.shared.User} is authorized to see it
     * @param id of the quiz
     * @return quiz entity
     */
    @PostAuthorize("returnObject.restricted == true ? hasAnyAuthority('ADMIN', 'SUPER_ADMIN') : true")
    Quiz getQuizById(long id);
}
