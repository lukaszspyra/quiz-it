package spyra.lukasz.javaquizzes.feature.quizselector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.shared.QuizRepository;

import java.util.List;

@Service
class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizMapper quizMapper;

    List<QuizView> findAll() {
        return quizMapper.toView(quizRepository.findAll());
    }

    QuizView getById(long id){
        return quizMapper.toView(quizRepository.getById(id));
    }

}
