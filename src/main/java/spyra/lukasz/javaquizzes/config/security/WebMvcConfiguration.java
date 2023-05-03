package spyra.lukasz.javaquizzes.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.CacheControl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * Configuration of Spring's autoconfigured controllers
 */
@Configuration
class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * Registers custom controller, used for custom login with view name
     * <p>
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

    /**
     * Add caching on the client side rules via Http headers.
     * Static resources are cached for 60 minutes, except restricted content. Further config in application.properties
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.maxAge(60, TimeUnit.MINUTES).cachePrivate().mustRevalidate());

        registry.addResourceHandler("/modern/js/*")
                .addResourceLocations("classpath:/static/modern/js/")
                .setCacheControl(CacheControl.noCache().cachePrivate().mustRevalidate());

        registry.addResourceHandler("/restricted/*")
                .addResourceLocations("classpath:/static/restricted/")
                .setCacheControl(CacheControl.noStore());

        registry.addResourceHandler("/recaptcha/api.js")
                .addResourceLocations("https://www.google.com/recaptcha/api.js")
                .setCacheControl(CacheControl.noStore());

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .setCacheControl(CacheControl.noStore());
    }
}
