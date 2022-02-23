package spyra.lukasz.javaquizzes.feature.usersmanagement.authority;


import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.User;

interface UserAuthorityRepository extends JpaRepository<User, Long> {
}
