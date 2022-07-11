## [QuizIT](https://quizitspace.herokuapp.com/)

---
Hobbyistic application created to help me in learning web app development. Simple idea of having private place for taking
coding quizzes evolved into public space for variety of IT puzzles topics. Implemented functionalities:
- Demo - Quiz samples of available quizzes (5 questions/no time limits/results and guests are not saved)
- User's registration - personal space with quiz results (authorization and authentication)
- Logged-in users have additional possibilities:
  - Quiz attempt are saved with score/time/given answers details
  - Time limits both for one question and for whole attempt
  - Profile with all registered data
  - Selected quizzes (consisting of 20 questions from multiple categories)
  - Random quizzes with chosen number of questions and category (created on-the-fly from [API](https://quizapi.io/))
  - Restricted contents for my private studies (here it all began...)

---
#### Technologies used:
- Java 18
- Spring Boot 2.7.1
- Spring Data JPA
- Spring Security
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
- google's reCaptcha
- deployed on Heroku platform

---
#### Local run config:
- start docker container with PostgreSQL database before running application:  
docker run --name quiz-it_db -e POSTGRES_USER=root -e POSTGRES_PASSWORD=pass -e POSTGRES_DB=quiz-it_db -d -p 5432:5432 postgres
- to use in-memory database: 
  - add h2 dependency to pom.xml (scope runtime)
  - set in application.properties:
    - spring.datasource.url=jdbc:h2:mem:dbname
    - spring.datasource.driverClassName=org.h2.Driver
    - spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    - spring.h2.console.enabled=true
  