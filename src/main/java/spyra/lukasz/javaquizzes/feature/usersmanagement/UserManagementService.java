package spyra.lukasz.javaquizzes.feature.usersmanagement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spyra.lukasz.javaquizzes.shared.AvailableRole;
import spyra.lukasz.javaquizzes.shared.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Contains logic for {@link User} management
 */
@Service
@RequiredArgsConstructor
class UserManagementService {

    private final UserManagementRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserAuthorityMapStructMapper mapper;


    Optional<ChangeUserRoleDTO> changeRole(long userId, AvailableRole newRole) {
        Optional<User> userById = userRepository.findByIdAndRoleNameNotLike(userId, newRole.name());
        if (userById.isEmpty() || userById.get().isSuperAdmin()) {
            return Optional.empty();
        }
        return performRoleChange(userById.get(), newRole);
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
