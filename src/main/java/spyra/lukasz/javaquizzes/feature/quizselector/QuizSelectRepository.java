package spyra.lukasz.javaquizzes.feature.quizselector;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.util.List;

interface QuizSelectRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findQuizzesByRestrictedFalseAndPredefinedTrueOrderByTitleAsc();

    List<Quiz> findQuizzesByRestrictedTrueOrderByTitleAsc();

    @PostAuthorize("returnObject.restricted == true ? hasAnyAuthority('ADMIN', 'SUPER_ADMIN') : true")
    Quiz getQuizById(long id);
}
