package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Question;

import java.util.List;
import java.util.stream.Collectors;

@Component
class QuestionMapper {

    @Autowired
    private AnswerMapper answerMapper;

    QuestionView toView(Question question) {
        QuestionView view = new QuestionView();
        view.setId(question.getId());
        view.setContent(question.getContent());
        view.setScore(question.getScore());
        view.setAnswers(answerMapper.toView(question.getAnswers()));
        return view;
    }

    List<QuestionView> toView(List<Question> questions) {
        return questions.stream()
                .map(this::toView)
                .collect(Collectors.toList());

    }
}
