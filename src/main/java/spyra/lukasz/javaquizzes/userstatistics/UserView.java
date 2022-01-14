package spyra.lukasz.javaquizzes.userstatistics;

import lombok.Getter;
import lombok.Setter;
import spyra.lukasz.javaquizzes.shared.Role;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

import java.util.List;

@Getter
@Setter
class UserView {

    private long id;

    private String name;

    private String email;

    private String password;

    private List<TakeQuiz> takenQuizzes;

    private Role role;

}
