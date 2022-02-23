package spyra.lukasz.javaquizzes.feature.usersmanagement.authority;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spyra.lukasz.javaquizzes.shared.AvailableRole;

@Controller
class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @PutMapping("/superadmin/authority/admin")
    String addAdminRole(@RequestParam(value = "id") long userId) {
        authorityService.changeRole(userId, AvailableRole.ADMIN);
        return "/superadmin";
    }

    @GetMapping("/superadmin")
    String showAllUsers(Model model) {
        model.addAllAttributes(authorityService.showAllUsersGroupedByRole());
        return "all-users";
    }
}
