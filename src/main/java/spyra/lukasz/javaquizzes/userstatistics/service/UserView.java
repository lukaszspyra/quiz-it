package spyra.lukasz.javaquizzes.userstatistics.service;

import lombok.Getter;
import lombok.Setter;
import spyra.lukasz.javaquizzes.model.TakeQuiz;
import spyra.lukasz.javaquizzes.userstatistics.repository.Role;

import java.util.List;

@Getter
@Setter
public class UserView {

    private long id;

    private String name;

    private String email;

    private String password;

    private List<TakeQuiz> takenQuizzes;

    private Role role;

}
