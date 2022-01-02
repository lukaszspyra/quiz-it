package spyra.lukasz.javaquizzes.quizselector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.quizselector.respository.QuizRepository;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizMapper quizMapper;

    public List<QuizView> findAll() {
        return quizMapper.toView(quizRepository.findAll());
    }

}
