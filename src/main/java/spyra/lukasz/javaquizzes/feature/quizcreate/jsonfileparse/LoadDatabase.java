package spyra.lukasz.javaquizzes.feature.quizcreate.jsonfileparse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(DbInitLoaderFromFile fromFile){

        return args -> {fromFile.loadQuizzesFromFile();};
    }


}
