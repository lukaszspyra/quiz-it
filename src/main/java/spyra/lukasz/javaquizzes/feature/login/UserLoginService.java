package spyra.lukasz.javaquizzes.feature.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.shared.User;

@Service
class UserLoginService implements UserDetailsService {


    @Autowired
    private LoginUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = repository.loginUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        return new MyUserPrincipal(foundUser);
    }

}
