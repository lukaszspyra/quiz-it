package spyra.lukasz.javaquizzes.shared;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Transactional
@Entity
@Table(name = "take_quiz_answers")
class TakeQuizAnswer {

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