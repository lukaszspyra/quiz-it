package spyra.lukasz.javaquizzes.feature.usersmanagement.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.shared.AvailableRole;
import spyra.lukasz.javaquizzes.feature.usersmanagement.RoleRepository;
import spyra.lukasz.javaquizzes.shared.User;

@Service
class UserRegisterService {

    private final UserRegisterRepository registerRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserRegisterService(UserRegisterRepository registerRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.registerRepository = registerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers new {@link User} in database, with {@link spyra.lukasz.javaquizzes.shared.Role Role} set to "USER".
     * Password is encoded before saving by concrete implementation of {@link PasswordEncoder}
     *
     * @param userDto      as data from view
     * @param assignedRole assigned from available roles
     * @return registered user with generated id
     * @throws UserAlreadyExistsException when {@link User} with given email is already registered in database
     */
    User registerAccount(NewUserDTO userDto, AvailableRole assignedRole) throws UserAlreadyExistsException {
        if (isRegistered(userDto)) {
            throw new UserAlreadyExistsException("There is already registered user with that email address: " + userDto.getEmail());
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encodePassword(userDto));
        user.setRole(roleRepository.findByName(assignedRole.name()));
        return registerRepository.save(user);
    }

    private String encodePassword(NewUserDTO userDto) {
        return passwordEncoder.encode(userDto.getPassword());
    }

    private boolean isRegistered(NewUserDTO userDto) {
        return registerRepository.findByEmail(userDto.getEmail()).isPresent();
    }
}
