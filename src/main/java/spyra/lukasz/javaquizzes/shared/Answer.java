package spyra.lukasz.javaquizzes.shared;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Transactional
@Entity
@Table(name = "answer")
class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private boolean correct;

    @NotNull
    private String content;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @NotNull
    @Valid
    private Question question;

}
