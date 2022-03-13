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

    QuizView getByIdNotRestricted(long id) {
        return quizMapper.toView(quizSelectRepository.findQuizByIdAndRestrictedFalse(id));
    }

    QuizView getByIdRestricted(long id) {
        return quizMapper.toView(quizSelectRepository.findQuizByIdAndRestrictedTrue(id));
    }

    List<QuizView> findRestricted() {
        return quizMapper.toView(quizSelectRepository.findQuizzesByRestrictedTrue());
    }
}
