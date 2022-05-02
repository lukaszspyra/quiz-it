package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps {@link Quiz} to {@link AttemptView}
 */
@Component
public class AttemptMapper {

    private final QuestionMapper questionMapper;

    public AttemptMapper(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    List<AttemptView> toView(List<Quiz> quizzes) {
        return quizzes.stream()
                .map(this::toView)
                .collect(Collectors.toList());
    }

    AttemptView toView(Quiz quiz) {
        AttemptView view = new AttemptView();
        view.setId(quiz.getId());
        view.setTitle(quiz.getTitle());
        view.setMaxScore(quiz.getMaxScore());
        view.setCreated(quiz.getCreated());
        view.setUpdated(quiz.getUpdated());
        view.setQuestions(questionMapper.toView(quiz.getQuestions()));
        return view;
    }

}
