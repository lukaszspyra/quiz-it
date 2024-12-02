# QuizIT

Web-app for taking IT quizzes, hosted on [Railway](https://railway.app) and [Neon](https://neon.tech).

### https://quizit.up.railway.app

---
### Summary:
Hobbyist application created to practise Java web development. Simple idea of having private place for taking
coding quizzes evolved into public space for variety of IT puzzles topics.

#### Implemented functionalities:
- Demo - Quiz samples of available quizzes (5 questions/no time limits/results and guests are not registered)
- User's registration - personal space with quiz results (authorization and authentication)
- Logged-in users have additional options:
  - Quiz attempts are saved with score/time/given answers details
  - Time limits both for one question and for whole attempt
  - Profile with all registered data
  - Selected quizzes (consisting of 20 questions from multiple categories)
  - Random quizzes with chosen number of questions and category (created on-the-fly from [API](https://quizapi.io/))
- Administrators:
  - Restricted contents
  - Users management (removal/role change)
  - Cache statistics


---
### Running the app locally:

1) Required: Docker Compose (multi-container setup)
    - simply run the app in IDE or CLI from root of the project:  
      ```docker compose up```


2) Required: Java17, instance of PostgreSQL database**
   - start PostgreSQL database, e.g. in docker container:  
   ```docker run -e POSTGRES_USER=root -e POSTGRES_PASSWORD=pass -e POSTGRES_DB=postgres -p 5432:5432 -d postgres```

   - run the app in IDE or CLI from root of the project:  
   ```./mvnw clean package```  
   ```java -jar target/quiz-it-1.0.0.jar```


** to use in-memory database instead:
add h2 dependency to pom.xml (scope runtime) and replace entries in application.properties by:

    ```spring.datasource.url=jdbc:h2:mem:dbname```
    ```spring.datasource.driverClassName=org.h2.Driver```
    ```spring.jpa.database-platform=org.hibernate.dialect.H2Dialect```
    ```spring.h2.console.enabled=true```

---
#### Sample logins with different roles (passwords hashed in DB):

```sadmin@test.pl / Password1```  
```admin@test.pl / Password1```  
```user@test.pl / Password2```  



---
#### Technologies used:
- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- Caffeine cache
- Hibernate
- Thymeleaf
- PostgreSQL
- Docker
- Lombok
- Jackson
- MapStruct
- JQuery/JS
- Maven
- TestNG
- JUnit
- Google's reCaptcha
