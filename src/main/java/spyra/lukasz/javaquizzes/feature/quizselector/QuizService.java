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

    List<QuizView> findNotRestricted() {
        return quizMapper.toView(quizSelectRepository.findQuizzesByRestrictedFalse());
    }

    List<QuizView> findRestricted() {
        return quizMapper.toView(quizSelectRepository.findQuizzesByRestrictedTrue());
    }

    QuizView getById(long id) {
        return quizMapper.toView(quizSelectRepository.getQuizById(id));
    }
}
