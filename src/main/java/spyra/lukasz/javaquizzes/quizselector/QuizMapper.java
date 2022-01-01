package spyra.lukasz.javaquizzes.quizselector;

import org.springframework.stereotype.Component;

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
