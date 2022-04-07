package nc.unc.ama.test_spring_app.controllers;

import nc.unc.ama.test_spring_app.dto.UserInfoDTO;
import nc.unc.ama.test_spring_app.entities.UserInfo;
import nc.unc.ama.test_spring_app.services.AuthService;
import nc.unc.ama.test_spring_app.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final AuthService authService;
    private final SecurityService securityService;

    @Autowired
    public AuthController(
        final AuthService authService,
        final SecurityService securityService
    ) {
        this.authService = authService;
        this.securityService = securityService;
    }

    @GetMapping("/registration")
    public String registration(final Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("userForm", new UserInfo());

        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registration(
        @Validated @ModelAttribute("userForm") final UserInfoDTO userForm,
        final BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        authService.registerUser(userForm);
        securityService.autoLogin(userForm.getLogin(), userForm.getPassword());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(
        final Model model,
        final String error,
        final String logout
    ) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Incorrect login or password!");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully!");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(final Model model) {
        return "welcome";
    }
}
