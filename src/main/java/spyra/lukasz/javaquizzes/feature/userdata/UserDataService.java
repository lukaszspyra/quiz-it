package spyra.lukasz.javaquizzes.feature.userdata;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserDataService {

    private final UserRepository userRepository;

    private final UserHomeMapper userMapper;

    /**
     * Gets data from database and sends to view mapped as DTO.
     * User data is cached for properties predefined time or until log out
     * @param email registered to existing {@link org.springframework.security.core.userdetails.User}
     * @return {@link UserView}
     */
    @Cacheable(cacheNames = "UserProfile")
    public UserView getUserDataByEmail(String email) {
        return userMapper.from(userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found.")));
    }
}
