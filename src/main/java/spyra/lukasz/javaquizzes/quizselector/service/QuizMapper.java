package spyra.lukasz.javaquizzes.quizselector.service;

import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.quizselector.respository.Quiz;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuizMapper {

    List<QuizView> toView(List<Quiz> quizzes) {
        return quizzes.stream()
                .map(this::toView)
                .collect(Collectors.toList());
    }

    QuizView toView(Quiz quizz) {
        QuizView view = new QuizView();
        view.setId(quizz.getId());
        view.setTitle(quizz.getTitle());
        view.setScore(quizz.getScore());
        view.setCreated(quizz.getCreated());
        view.setUpdated(quizz.getUpdated());
        return view;
    }

}
