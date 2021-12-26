package spyra.lukasz.javaquizzes.model;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Transactional
@Entity
@Table(name = "take_quiz_answers")
public class TakeQuizAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int score;

    @NotNull
    private LocalDateTime start;

    @NotNull
    private LocalDateTime finish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "takeQuiz_id")
    @NotNull
    @Valid
    private TakeQuiz takeQuiz;

}