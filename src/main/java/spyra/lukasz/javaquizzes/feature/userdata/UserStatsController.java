package spyra.lukasz.javaquizzes.feature.userdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spyra.lukasz.javaquizzes.feature.login.MyUserPrincipal;

import java.security.Principal;

@Controller
class UserStatsController {

    @Autowired
    private UserDataService userService;

    @GetMapping("/")
    public String home(Principal principal, Authentication authentication, Model model) {
        model.addAttribute("user", userService.getUserDataByEmail(principal.getName()));
        //TODO: for research purposes: check user data saved in session after modification in database. After checks
        //MyUserPrincipal can be pacakge-private
        MyUserPrincipal test = (MyUserPrincipal) authentication.getPrincipal();
        return "home";
    }

}
