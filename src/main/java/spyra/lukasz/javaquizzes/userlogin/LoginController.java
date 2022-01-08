package spyra.lukasz.javaquizzes.userlogin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    /*Todo
    Implement controller login methods
     */

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping({ "/menu"})
    public String menu() {
        return "menu";
    }
}
