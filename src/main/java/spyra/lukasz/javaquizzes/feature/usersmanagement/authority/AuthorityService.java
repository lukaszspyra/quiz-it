package spyra.lukasz.javaquizzes.feature.usersmanagement.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.shared.AvailableRole;
import spyra.lukasz.javaquizzes.shared.RoleRepository;
import spyra.lukasz.javaquizzes.shared.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class AuthorityService {

    @Autowired
    private UserAuthorityRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserAuthorityMapStructMapper mapper;

    Optional<ChangeUserRoleDTO> changeRole(long userId, AvailableRole newRole) {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isPresent() && userId != 1) {
            final User foundUser = byId.get();
            foundUser.setRole(roleRepository.findByName(newRole.name()));
            return Optional.of(mapper.toView(userRepository.save(foundUser)));
        }
        return Optional.empty();
    }

    Map<String, List<ChangeUserRoleDTO>> showAllUsersGroupedByRole() {
        final List<ChangeUserRoleDTO> allUserDTO = mapper.toView(userRepository.findAll());
        return allUserDTO.stream()
                .collect(Collectors.groupingBy(ChangeUserRoleDTO::getRole));
    }
}
