package spyra.lukasz.javaquizzes.feature.userdata;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spyra.lukasz.javaquizzes.feature.login.MyUserPrincipal;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
class UserStatsController {

    private final UserDataService userService;

    @GetMapping("/profile")
    public String userProfile(Principal principal, Authentication authentication, Model model) {
        model.addAttribute("user", userService.getUserDataByEmail(principal.getName()));
        return "profile";
    }

    @GetMapping({"/", "/home"})
    public String commonIndex() {
        return "index";
    }

    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }

}
