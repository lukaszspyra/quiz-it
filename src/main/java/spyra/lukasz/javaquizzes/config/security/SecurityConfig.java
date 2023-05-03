package spyra.lukasz.javaquizzes.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import spyra.lukasz.javaquizzes.config.CacheConfiguration;
import spyra.lukasz.javaquizzes.shared.AvailableRole;

import java.util.Arrays;

import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.*;

/**
 * Configuration of Spring Security
 * </p>
 * Enables {@link org.springframework.security.access.prepost.PreAuthorize}
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CacheConfiguration cacheConfiguration;

    /**
     * Array of resources endpoints, that shall be available without authentication
     */
    private final String[] resources = new String[]{
            "/modern/**", "/css/**", "/images/**", "/js/**", "/plugins/**", "/scss/**", "/fonts/**", "/icons/**"
    };

    /**
     * Array of Admins names, used for securing administration endpoints
     */
    private final String[] admins = new String[]{AvailableRole.SUPER_ADMIN.name(), AvailableRole.ADMIN.name()};
    private final String[] users = Arrays.stream(AvailableRole.values())
            .map(AvailableRole::name)
            .toArray(String[]::new);

    /**
     * Provides bean of BCrypt as default password encoder for users passwords hashing
     *
     * @return new instance of BCrypt encoder
     */
    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Main security config
     *
     * <ul>
     *     <li>
     *         Configures endpoints for admins, users and visitors
     *     </li>
     *     <li>
     *         Provides custom login/logout endpoints, that can be used for custom views
     *     </li>
     *     <li>
     *         Clears cache with user attempt details and user profile upon log out
     *     </li>
     * </ul>
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/healthz").permitAll()
                .antMatchers(resources).permitAll()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/demo/**").permitAll()
                .antMatchers("/register").permitAll()

                .antMatchers("/superadmin/**").hasAuthority(AvailableRole.SUPER_ADMIN.name())
                .antMatchers("/admin/**", "/restricted/**").hasAnyAuthority(admins)
                .antMatchers("/user").hasAnyAuthority(users)
                .anyRequest()
                .authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(CACHE, COOKIES, STORAGE)))
                .addLogoutHandler(cacheConfiguration.clearCache())
                .permitAll()
                .logoutSuccessUrl("/login?logout");
    }
}
