package spyra.lukasz.javaquizzes.feature.usersmanagement.login;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spyra.lukasz.javaquizzes.shared.User;

import javax.persistence.Transient;
import java.util.Collection;
import java.util.Collections;

/**
 * Provides custom implementation of UserDetails interface used by Spring.
 *
 * {@link MyUserPrincipal#getUsername()} returns {@link MyUserPrincipal#email} in order for Spring to use it as User's login.
 */
public class MyUserPrincipal implements UserDetails {

    private final long id;
    private final String name;
    private final String roleName;
    private final String email;

    @Transient
    private final String password;

    public MyUserPrincipal(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.roleName = user.getRole().getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
