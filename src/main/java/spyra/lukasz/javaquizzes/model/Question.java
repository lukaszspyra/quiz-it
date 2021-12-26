package spyra.lukasz.javaquizzes.model;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private int score;

    @NotNull
    private String content;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @NotNull
    @Valid
    private Quiz quiz;


    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.MERGE})
    @NotNull
    @Valid
    private List<Answer> answers = new ArrayList<>();

}