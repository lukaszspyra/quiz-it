package spyra.lukasz.javaquizzes.quizselector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizMapper quizMapper;

    List<QuizView> findAll() {
        return quizMapper.toView(quizRepository.findAll());
    }

}
