package spyra.lukasz.javaquizzes.feature.usersmanagement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spyra.lukasz.javaquizzes.shared.AvailableRole;

@Controller
@RequestMapping("/superadmin/management")
class UsersManagementController {

    @Autowired
    private AuthorityService authorityService;

    @PutMapping("/authority/admin")
    String addAdminRole(@RequestParam(value = "id") long userId) {
        authorityService.changeRole(userId, AvailableRole.ADMIN);
        return "redirect:/superadmin/management";
    }

    @PutMapping("/authority/user")
    String removeAdminRole(@RequestParam(value = "id") long adminId) {
        authorityService.changeRole(adminId, AvailableRole.USER);
        return "redirect:/superadmin/management";
    }

    @GetMapping
    String showAllUsers(Model model) {
        model.addAllAttributes(authorityService.showAllUsersGroupedByRole());
        return "all-users";
    }
}
