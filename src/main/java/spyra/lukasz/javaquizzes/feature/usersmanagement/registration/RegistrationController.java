package spyra.lukasz.javaquizzes.feature.usersmanagement.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spyra.lukasz.javaquizzes.feature.usersmanagement.registration.recaptcha.InvalidReCaptchaException;
import spyra.lukasz.javaquizzes.feature.usersmanagement.registration.recaptcha.ReCaptchaService;
import spyra.lukasz.javaquizzes.shared.AvailableRole;

import javax.validation.Valid;

/**
 * Responsible for registration of new Users
 */
@Controller
@RequiredArgsConstructor
class RegistrationController {

    private final UserRegisterService registerService;

    private final ReCaptchaService reCaptchaService;

    @GetMapping("/register")
    public String showRegistrationForm(NewUserDTO newUserDTO) {
        return "register";
    }

    /**
     * Registers new User based on form data received by POST
     * <p>
     * If there are validating errors in the filled up form, or User already exists, returns to registration with proper message.
     *
     * @param newUserDTO    tied to DTO object {@link NewUserDTO}
     * @param bindingResult results of validated data
     * @param model         passes successful registration message
     * @return proper view (login/registration on success/failure of registration process)
     */
    @PostMapping("/register")
    public String registerUserAccount(
            @RequestParam(value = "g-recaptcha-response") String captcha,
            @Valid NewUserDTO newUserDTO,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors() || captcha == null || captcha.isBlank()) {
            //TODO:add captcha error to model
            return "register";
        }

        try {
            reCaptchaService.processResponse(captcha);
            registerService.registerAccount(newUserDTO, AvailableRole.USER);
        } catch (UserAlreadyExistsException | InvalidReCaptchaException ex) {
            bindingResult.rejectValue("email", "newUserDTO.email", ex.getMessage());
            return "register";
        }
        model.addAttribute("registrationsuccess", "You have been successfully registered");
        return "/login";
    }
}
