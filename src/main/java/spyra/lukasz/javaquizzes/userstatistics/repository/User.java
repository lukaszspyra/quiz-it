package spyra.lukasz.javaquizzes.userstatistics.repository;

import lombok.Getter;
import lombok.Setter;
import spyra.lukasz.javaquizzes.model.TakeQuiz;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.MERGE})
    @Valid
    private List<TakeQuiz> takenQuizzes = new ArrayList<>();

}