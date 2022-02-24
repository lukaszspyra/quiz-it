package spyra.lukasz.javaquizzes.feature.usersmanagement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spyra.lukasz.javaquizzes.shared.AvailableRole;

@Controller
@RequestMapping("/superadmin/user-management")
class UsersManagementController {

    @Autowired
    private AuthorityService authorityService;

    @PutMapping("/authority/admin-role")
    String addAdminRole(@RequestParam(value = "id") long userId) {
        authorityService.changeRole(userId, AvailableRole.ADMIN);
        return "redirect:/superadmin/user-management/users";
    }

    @PutMapping("/authority/user-role")
    String removeAdminRole(@RequestParam(value = "id") long adminId) {
        //TODO: use Data from Optional or simplify to boolean flag
        authorityService.changeRole(adminId, AvailableRole.USER);
        return "redirect:/superadmin/user-management/users";
    }


    @GetMapping("/users")
    String showAllUsers(@RequestParam String success,
                        Model model) {
        model.addAllAttributes(authorityService.showAllUsersGroupedByRole());
        return "all-users";
    }
}
