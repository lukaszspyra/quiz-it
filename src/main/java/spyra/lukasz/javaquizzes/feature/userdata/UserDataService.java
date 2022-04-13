package spyra.lukasz.javaquizzes.feature.userdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class UserDataService {

    private final UserRepository userRepository;

    private final UserHomeMapper userMapper;

    @Autowired
    UserDataService(UserRepository userRepository, UserHomeMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Gets data from database and sends to view mapped as DTO
     * @param email registered to existing {@link org.springframework.security.core.userdetails.User}
     * @return {@link UserView}
     */
    UserView getUserDataByEmail(String email) {
        return userMapper.from(userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found.")));
    }
}
