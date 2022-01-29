package spyra.lukasz.javaquizzes.feature.quizselector;

import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
class QuizMapper {

    List<QuizView> toView(List<Quiz> quizzes) {
        return quizzes.stream()
                .map(this::toView)
                .collect(Collectors.toList());
    }

    QuizView toView(Quiz quiz) {
        QuizView view = new QuizView();
        view.setId(quiz.getId());
        view.setTitle(quiz.getTitle());
        view.setScore(quiz.getMaxScore());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        view.setCreated(quiz.getCreated().format(formatter));
        view.setUpdated(quiz.getUpdated().format(formatter));
        return view;
    }

}
