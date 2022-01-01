package spyra.lukasz.javaquizzes.userstatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    UserView getUserStatistics(String email) {
        return userMapper.toView(userRepository.findUserByEmail(email));
    }

}
