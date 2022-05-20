package spyra.lukasz.javaquizzes.feature.quizselect;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Gets mapped {@link spyra.lukasz.javaquizzes.shared.Quiz} entities to DTO {@link QuizView} from database
 */
@Service
@RequiredArgsConstructor
class QuizService {

    private final QuizSelectRepository quizSelectRepository;

    private final QuizToViewMapper quizToViewMapper;

    List<QuizView> findPredefinedNotRestrictedNotDemo() {
        return quizToViewMapper.toView(quizSelectRepository.findQuizzesByRestrictedFalseAndPredefinedTrueAndDemoOrderByTitleAsc(false));
    }

    List<QuizView> findRestricted() {
        return quizToViewMapper.toView(quizSelectRepository.findQuizzesByRestrictedTrueOrderByTitleAsc());
    }

    QuizView getById(long id) {
        return quizToViewMapper.toView(quizSelectRepository.getQuizById(id));
    }
}
