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

    private final QuizMapper quizMapper;

    @Autowired
    QuizService(final QuizSelectRepository quizSelectRepository, final QuizMapper quizMapper) {
        this.quizSelectRepository = quizSelectRepository;
        this.quizMapper = quizMapper;
    }

    List<QuizView> findPredefinedNotRestricted() {
        return quizMapper.toView(quizSelectRepository.findQuizzesByRestrictedFalseAndPredefinedTrueOrderByTitleAsc());
    }

    List<QuizView> findRestricted() {
        return quizMapper.toView(quizSelectRepository.findQuizzesByRestrictedTrueOrderByTitleAsc());
    }

    QuizView getById(long id) {
        return quizMapper.toView(quizSelectRepository.getQuizById(id));
    }
}
