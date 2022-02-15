package spyra.lukasz.javaquizzes.feature.usersmanagement.registration;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

class RegistrationController {

    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model) {
        NewUserDTO userDto = new NewUserDTO();
        model.addAttribute("user", userDto);
        return "user-registration";
    }

}
