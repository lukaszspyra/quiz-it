package spyra.lukasz.javaquizzes.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

/**
 * Configuration of RestOperations factory method
 */
@Configuration
public class RestOperationsBeanCreator {

    /**
     * Creates bean {@link RestOperations} for retrieval post results of google's ReCaptcha verification
     *
     * @param restTemplateBuilder resolved automatically by Spring
     * @return instance of RestOperations
     */
    @Bean
    public RestOperations restOperations(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

}
