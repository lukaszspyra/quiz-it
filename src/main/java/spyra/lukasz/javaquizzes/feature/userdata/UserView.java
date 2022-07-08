package spyra.lukasz.javaquizzes.feature.userdata;

import lombok.Getter;
import lombok.Setter;
import spyra.lukasz.javaquizzes.shared.Role;

import java.util.List;

/**
 * DTO for User data presentation
 */
@Getter
@Setter
class UserView {

    private long id;

    private String name;

    private String email;

    private String password;

    private List<TakeQuizView> takenQuizzes;

    private Role role;

}
