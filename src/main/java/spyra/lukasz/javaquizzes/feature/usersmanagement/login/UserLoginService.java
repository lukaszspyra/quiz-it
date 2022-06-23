package spyra.lukasz.javaquizzes.feature.usersmanagement.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.shared.User;

@Service
@RequiredArgsConstructor
class UserLoginService implements UserDetailsService {

    private final LoginUserRepository repository;

    /**
     * Delivers custom implementation of Spring's UserDetails interface, used for login process.
     *
     * @param username login email
     * @return {@link MyUserPrincipal}
     * @throws UsernameNotFoundException if user not registered yet
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = repository.loginUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        return new MyUserPrincipal(foundUser);
    }

}
