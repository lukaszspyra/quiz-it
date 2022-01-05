package spyra.lukasz.javaquizzes.userstatistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.userstatistics.repository.UserRepository;

@Service
public class StatsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserView getUserStatistics(String email) {
        return userMapper.toView(userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found.")));
    }

}
