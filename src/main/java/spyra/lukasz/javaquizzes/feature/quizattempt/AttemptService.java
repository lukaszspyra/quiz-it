package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.feature.quizselector.QuizRepository;

@Service
class AttemptService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private AttemptMapper attemptMapper;

    AttemptView getQuizById(long id) {
        return attemptMapper.toView(quizRepository.getById(id));
    }

}
