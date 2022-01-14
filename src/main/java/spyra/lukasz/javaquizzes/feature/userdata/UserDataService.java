package spyra.lukasz.javaquizzes.feature.userdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserView getUserDataByEmail(String email) {
        return userMapper.toView(userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found.")));
    }

}
