package spyra.lukasz.javaquizzes.shared;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Entity
@Getter
@Setter
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private long apiId;

    @NotNull
    private int score;

    @NotNull
    private String content;

    @ManyToMany
    @JoinTable(name = "quiz_question",
            joinColumns = @JoinColumn(name = "quiz_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"))
    @NotNull
    @Valid
    private List<Quiz> quiz;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.MERGE})
    @NotNull
    @Valid
    private List<Answer> answers = new ArrayList<>();
}
