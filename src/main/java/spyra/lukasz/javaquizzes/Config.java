package spyra.lukasz.javaquizzes;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestOperations;

/**
 * Configuration of RestOperations factory method
 */
@Configuration
public class Config {

    @Bean
    public RestOperations restOperations(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

}
