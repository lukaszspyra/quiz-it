package spyra.lukasz.javaquizzes.feature.userdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.User;

@Component
class UserMapper {

    @Autowired
    private UserHomeMapper userHomeMapper;

    UserView toView(User user) {
        return userHomeMapper.from(user);
    }
}
