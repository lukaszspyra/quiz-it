package spyra.lukasz.javaquizzes.feature.usersmanagement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spyra.lukasz.javaquizzes.shared.AvailableRole;

@Controller
@RequestMapping("/superadmin/user-management")
class UsersManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @PutMapping("/authority/admin-role")
    String addAdminRole(@RequestParam(value = "id") long userId) {
        userManagementService.changeRole(userId, AvailableRole.ADMIN);
        return "redirect:/superadmin/user-management/users";
    }

    @PutMapping("/authority/user-role")
    String removeAdminRole(@RequestParam(value = "id") long adminId) {
        //TODO: use Data from Optional or simplify to boolean flag
        userManagementService.changeRole(adminId, AvailableRole.USER);
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
        return "all-users";
    }
}
