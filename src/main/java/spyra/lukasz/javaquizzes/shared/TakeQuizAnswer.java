package spyra.lukasz.javaquizzes.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "take_quiz_answers")
@Getter
public final class TakeQuizAnswer {

    public TakeQuizAnswer(TakeQuiz takeQuiz, Question question, Answer answer) {
        this.takeQuiz = takeQuiz;
        this.question = question;
        this.answer = answer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "take_quiz_id")
    @NotNull
    @Valid
    private TakeQuiz takeQuiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @NotNull
    @Valid
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    @NotNull
    @Valid
    private Answer answer;
}
