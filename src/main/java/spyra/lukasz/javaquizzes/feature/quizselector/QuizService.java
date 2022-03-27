package spyra.lukasz.javaquizzes.feature.quizselector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class QuizService {

    @Autowired
    private QuizSelectRepository quizSelectRepository;

    @Autowired
    private QuizMapper quizMapper;

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
