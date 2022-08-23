package spyra.lukasz.javaquizzes.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import spyra.lukasz.javaquizzes.config.security.SecurityConfig;

/**
 * Configuration of Spring's autoconfigured controllers
 */
@Configuration
class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * Registers custom controller, used for custom login with view name
     *
     * Spring uses default controllers/endpoints for login. In order to use custom login page with default endpoint, it
     * needs to be added to Spring controllers and permitted for all users in {@link SecurityConfig#configure(HttpSecurity)}
     *
     * @param registry used by Spring
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

}
