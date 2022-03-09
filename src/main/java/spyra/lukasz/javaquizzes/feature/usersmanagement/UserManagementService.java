package spyra.lukasz.javaquizzes.feature.usersmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spyra.lukasz.javaquizzes.shared.AvailableRole;
import spyra.lukasz.javaquizzes.shared.RoleRepository;
import spyra.lukasz.javaquizzes.shared.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Contains logic for {@link User} management
 */
@Service
class UserManagementService {

    @Autowired
    private UserManagementRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserAuthorityMapStructMapper mapper;

    Optional<ChangeUserRoleDTO> changeRole(long userId, AvailableRole newRole) {
        if (isSuperAdmin(userId)) {
            return Optional.empty();
        }
        Optional<User> userById = userRepository.findByIdAndRoleNameNotLike(userId, newRole.name());
        if (userById.isPresent()) {
            return performRoleChange(userById.get(), newRole);
        }
        return Optional.empty();
    }

    private boolean isSuperAdmin(long userId) {
        return userId == 1;
    }

    @Transactional
    Optional<ChangeUserRoleDTO> performRoleChange(User foundUser, AvailableRole newRole) {
        foundUser.setRole(roleRepository.findByName(newRole.name()));
        return Optional.of(mapper.toView(userRepository.save(foundUser)));
    }

    Map<String, List<ChangeUserRoleDTO>> showAllUsersGroupedByRole() {
        final List<ChangeUserRoleDTO> allUserDTO = mapper.toView(userRepository.findAll());
        return allUserDTO.stream()
                .collect(Collectors.groupingBy(ChangeUserRoleDTO::getRole));
    }

    @Transactional
    void deleteById(long idToDelete) {
        userRepository.deleteById(idToDelete);
    }
}
