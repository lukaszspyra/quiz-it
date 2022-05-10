package spyra.lukasz.javaquizzes.feature.quizselect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Gets mapped {@link spyra.lukasz.javaquizzes.shared.Quiz} entities to DTO {@link QuizView} from database
 */
@Service
class QuizService {

    private final QuizSelectRepository quizSelectRepository;

    private final QuizToViewMapper quizToViewMapper;

    @Autowired
    QuizService(final QuizSelectRepository quizSelectRepository, final QuizToViewMapper quizToViewMapper) {
        this.quizSelectRepository = quizSelectRepository;
        this.quizToViewMapper = quizToViewMapper;
    }

    List<QuizView> findPredefinedNotRestricted() {
        return quizToViewMapper.toView(quizSelectRepository.findQuizzesByRestrictedFalseAndPredefinedTrueOrderByTitleAsc());
    }

    List<QuizView> findRestricted() {
        return quizToViewMapper.toView(quizSelectRepository.findQuizzesByRestrictedTrueOrderByTitleAsc());
    }

    QuizView getById(long id) {
        return quizToViewMapper.toView(quizSelectRepository.getQuizById(id));
    }
}
