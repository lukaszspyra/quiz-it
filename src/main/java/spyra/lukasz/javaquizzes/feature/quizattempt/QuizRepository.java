package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import spyra.lukasz.javaquizzes.shared.Quiz;

interface QuizRepository extends JpaRepository<Quiz, Long> {

    @PostAuthorize("returnObject.restricted == true ? hasAnyAuthority('ADMIN', 'SUPER_ADMIN') : true")
    Quiz findQuizById(long id);
}
