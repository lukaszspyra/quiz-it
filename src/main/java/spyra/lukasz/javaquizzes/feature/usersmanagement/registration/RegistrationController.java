package spyra.lukasz.javaquizzes.feature.usersmanagement.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spyra.lukasz.javaquizzes.shared.AvailableRole;

import javax.validation.Valid;

/**
 * Responsible for registration of new Users
 */
@Controller
@RequiredArgsConstructor
class RegistrationController {

    private final UserRegisterService registerService;

    @GetMapping("/register")
    public String showRegistrationForm(NewUserDTO newUserDTO) {
        return "register";
    }

    /**
     * Registers new User based on form data received by POST
     *
     * If there are validating errors in the filled up form, or User already exists, returns to registration with proper message.
     * @param newUserDTO tied to DTO object {@link NewUserDTO}
     * @param bindingResult results of validated data
     * @param model passes successful registration message
     * @return proper view (login/registration on success/failure of registration process)
     */
    @PostMapping("/register")
    public String registerUserAccount(
            @Valid NewUserDTO newUserDTO,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            registerService.registerAccount(newUserDTO, AvailableRole.USER);
        } catch (UserAlreadyExistsException ex) {
            bindingResult.rejectValue("email", "newUserDTO.email", ex.getMessage());
            return "register";
        }
        model.addAttribute("registrationsuccess", "You have been successfully registered");
        return "/login";
    }
}
