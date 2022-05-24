package spyra.lukasz.javaquizzes.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Entity
@Getter
@Setter
@Table(name = "question")
@NoArgsConstructor
@AllArgsConstructor
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

    @NotNull
    private String difficulty;

    @ManyToMany
    @JoinTable(name = "quiz_question",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "quiz_id"))
    @NotNull
    @Valid
    private List<Quiz> quizzes = new LinkedList<>();

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.MERGE})
    @NotNull
    @Valid
    private List<Answer> answers = new ArrayList<>();


    /**
     * Filters available answers based on Ids
     *
     * @param selectedIds for answers filtering
     * @return list of answers filtered by selected Ids
     */
    public List<Answer> selectAnswers(List<Long> selectedIds){
        return answers.stream()
                .filter(q -> selectedIds.contains(q.getId()))
                .collect(Collectors.toUnmodifiableList());
    }


}
