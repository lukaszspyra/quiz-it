package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Answer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps {@link Answer} entity to view
 */
@Component
class AnswerMapper {

    AnswerView toView(Answer answer) {
        AnswerView view = new AnswerView();
        view.setId(answer.getId());
        view.setCorrect(answer.isCorrect());
        view.setContent(answer.getContent());
        return view;
    }

    List<AnswerView> toView(List<Answer> answers) {
        return answers.stream()
                .map(this::toView)
                .collect(Collectors.toUnmodifiableList());

    }
}
