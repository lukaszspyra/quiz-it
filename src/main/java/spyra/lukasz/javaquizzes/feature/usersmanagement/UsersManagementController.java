package spyra.lukasz.javaquizzes.feature.usersmanagement;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spyra.lukasz.javaquizzes.shared.AvailableRole;

import java.util.Optional;

/**
 * Perform various data manipulation on existing Users:
 *
 * <p>
 *     <ul>
 * <li> changes User's {@link spyra.lukasz.javaquizzes.shared.Role}
 * <li> deletes {@link spyra.lukasz.javaquizzes.shared.User}
 * <li> presents all registered Users
 *     </ul>
 * </p>
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/superadmin/user-management")
class UsersManagementController {

    private final UserManagementService userManagementService;

    @PutMapping("/users/authority/admin-role")
    String addAdminRole(@RequestParam(value = "id") long userId,
                        RedirectAttributes redirectAttributes) {
        final Optional<ChangeUserRoleDTO> roleChanged = userManagementService.changeRole(userId, AvailableRole.ADMIN);
        roleChanged.ifPresent(newAdmin -> redirectAttributes.addFlashAttribute("message", "Admin role will be set after re-login, for learner: " + newAdmin.getEmail()));
        return "redirect:/superadmin/user-management/users";
    }

    @PutMapping("/users/authority/user-role")
    String removeAdminRole(@RequestParam(value = "id") long adminId,
                           RedirectAttributes redirectAttributes) {
        final Optional<ChangeUserRoleDTO> roleChanged = userManagementService.changeRole(adminId, AvailableRole.USER);
        roleChanged.ifPresent(newUser -> redirectAttributes.addFlashAttribute("message", "User role will be set after re-login,for learner: " + newUser.getEmail()));
        return "redirect:/superadmin/user-management/users";
    }

    @DeleteMapping("/users")
    String deleteUser(@RequestParam(value = "id") long idToDelete,
                      @RequestParam(value = "email") String emailToDelete,
                      RedirectAttributes redirectAttributes) {
        userManagementService.deleteById(idToDelete);
        redirectAttributes.addFlashAttribute("message", "Deleted learner with email: " + emailToDelete);
        return "redirect:/superadmin/user-management/users";
    }

    @GetMapping("/users")
    String showAllUsers(Model model) {
        model.addAllAttributes(userManagementService.showAllUsersGroupedByRole());
        return "user-management";
    }
}
