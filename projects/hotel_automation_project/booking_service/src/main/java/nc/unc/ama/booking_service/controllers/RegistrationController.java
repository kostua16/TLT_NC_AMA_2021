package nc.unc.ama.booking_service.controllers;

import nc.unc.ama.booking_service.entities.Registration;
import nc.unc.ama.booking_service.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/Registration")
public class RegistrationController {

    private final RegistrationService regService;

    @Autowired
    public RegistrationController(RegistrationService regService) {
        this.regService = regService;
    }

    @PostMapping(path = "/inputData")
    public void inputData(@RequestBody String login, String password){

    }

    @GetMapping(path = "/viewRegisteredUsers/{registrationId}")
    public Registration viewRegisteredRooms(@PathVariable("registrationId") String login, String password, Long registrationId)
    {
        return regService.getRegisteredUsers(login, password, registrationId);
    }
}
