package spyra.lukasz.javaquizzes.feature.usersmanagement.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.shared.AvailableRole;
import spyra.lukasz.javaquizzes.shared.RoleRepository;
import spyra.lukasz.javaquizzes.shared.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
class AuthorityService {

    @Autowired
    private UserAuthorityRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserAuthorityMapStructMapper mapper;

    ChangeUserRoleDTO changeRole(long userId, AvailableRole newRole) {
        User byId = userRepository.getById(userId);
        byId.setRole(roleRepository.findByName(newRole.name()));
        return mapper.toView(userRepository.save(byId));
    }

    Map<String, List<ChangeUserRoleDTO>> showAllUsersGroupedByRole() {
        final List<ChangeUserRoleDTO> allUserDTO = mapper.toView(userRepository.findAll());
        return allUserDTO.stream()
                .collect(Collectors.groupingBy(ChangeUserRoleDTO::getRole));
    }
}
