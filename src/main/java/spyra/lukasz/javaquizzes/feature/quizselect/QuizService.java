package spyra.lukasz.javaquizzes.feature.quizselect;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Gets mapped {@link spyra.lukasz.javaquizzes.shared.Quiz} entities to DTO {@link QuizView} from database
 */
@Service
@RequiredArgsConstructor
class QuizService {

    private final QuizSelectRepository quizSelectRepository;

    private final QuizToViewMapper quizToViewMapper;

    /**
     * Find entities and map to view, result is cached for properties predefined time
     * @return list of quizzes mapped to QuizView
     */
    @Cacheable(cacheNames = "QuizzesPredefinedNotRestrictedNotDemo", key = "#root.methodName")
    public List<QuizView> findPredefinedNotRestrictedNotDemo() {
        return quizToViewMapper.toView(quizSelectRepository.findQuizzesByRestrictedFalseAndPredefinedTrueAndDemoOrderByTitleAsc(false));
    }

    @Cacheable(cacheNames = "RestrictedQuizzes", key = "#root.methodName")
    public List<QuizView> findRestricted() {
        return quizToViewMapper.toView(quizSelectRepository.findQuizzesByRestrictedTrueOrderByTitleAsc());
    }

    QuizView getById(UUID id) {
        return quizToViewMapper.toView(quizSelectRepository.getQuizById(id));
    }
}
