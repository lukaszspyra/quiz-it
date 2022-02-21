package spyra.lukasz.javaquizzes.feature.usersmanagement.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spyra.lukasz.javaquizzes.shared.AvailableRole;
import spyra.lukasz.javaquizzes.shared.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
class RegistrationController {

    @Autowired
    private UserRegisterService registerService;


    @GetMapping("/user/register")
    public String showRegistrationForm(NewUserDTO newUserDTO) {
        return "user-registration";
    }


    @PostMapping("/user/register")
    public String registerUserAccount(
            @Valid NewUserDTO newUserDTO,
            BindingResult bindingResult,
            Model model){
        if (bindingResult.hasErrors()) {
            return "user-registration";
        }
        User registered;
        try {
            registered = registerService.registerAccount(newUserDTO, AvailableRole.USER);
        } catch (UserAlreadyExistsException ex) {
            bindingResult.rejectValue("email", "newUserDTO.email", ex.getMessage());
            return "user-registration";
        }
        model.addAttribute("registrationsuccess", "You have been successfully registered.");
        return "/login";
    }
}
