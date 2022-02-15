package spyra.lukasz.javaquizzes.feature.usersmanagement.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.shared.User;

@Service
class UserRegisterService {

    @Autowired
    private UserRegisterRepository registerRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Registers new {@link User} in database, with {@link spyra.lukasz.javaquizzes.shared.Role Role} set to "USER"
     *
     * @param userDto as data from view
     * @return registered user with generated id
     * @throws UserAlreadyExistsException when {@link User} with given email is already registered in database
     */
    User registerAccount(NewUserDTO userDto) throws UserAlreadyExistsException {
        if (isRegistered(userDto)) {
            throw new UserAlreadyExistsException("There is already registered user with that email address: " + userDto.getEmail());
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(roleRepository.findByName("USER"));
        return registerRepository.save(user);
    }

    private boolean isRegistered(NewUserDTO userDto) {
        return registerRepository.findByEmail(userDto.getEmail()).isPresent();
    }
}
