package spyra.lukasz.javaquizzes.userlogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.userstatistics.repository.User;
import spyra.lukasz.javaquizzes.userstatistics.repository.UserRepository;

@Service
class UserLoginService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        return new MyUserPrincipal(foundUser);
    }

}
