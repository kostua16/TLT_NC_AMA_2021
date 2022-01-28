package nc.unc.ama.test_spring_app.controllers;

import nc.unc.ama.test_spring_app.dto.UserInfoDTO;
import nc.unc.ama.test_spring_app.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class AuthRestController {

    private final AuthService authService;

    @Autowired
    public AuthRestController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody final UserInfoDTO userInfoDTO) {
        return this.authService.registerUser(userInfoDTO);
    }
}
