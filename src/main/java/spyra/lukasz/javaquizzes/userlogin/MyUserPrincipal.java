package spyra.lukasz.javaquizzes.userlogin;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spyra.lukasz.javaquizzes.userstatistics.User;

import javax.persistence.Transient;
import java.util.Collection;
import java.util.Collections;

public class MyUserPrincipal implements UserDetails {

    private long id;
    private String name;
    private String roleName;
    private String email;

    @Transient
    private String password;

    public MyUserPrincipal(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.roleName = user.getRole().getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
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
