package spyra.lukasz.javaquizzes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    String[] resources = new String[]{
            "/userlogin/**", "/css/**", "/fonts/**", "/images/**", "/js/**", "/scss/**", "/icons/**", "/img/**"};

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(resources).permitAll()
                .antMatchers("/menu").permitAll()
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/user").hasAnyAuthority("USER", "ADMIN")
                .anyRequest()
                .authenticated()

                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .defaultSuccessUrl("/menu");

    }

}
