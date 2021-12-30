package spyra.lukasz.javaquizzes.quizselector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.model.Quiz;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    List<Quiz> findAll(){
        return quizRepository.findAll();
    }


}
