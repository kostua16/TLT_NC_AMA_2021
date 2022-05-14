package nc.unc.ama.guest.controllers;

import nc.unc.ama.guest.dto.RegDTO;
import nc.unc.ama.guest.entities.ConfirmationToken;
import nc.unc.ama.guest.entities.Guest;
import nc.unc.ama.guest.services.GuestRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/registration")
public class GuestRegistration {
    private final GuestRegService guestRegService;

    @Autowired
    public GuestRegistration(GuestRegService guestRegService) {
        this.guestRegService = guestRegService;
    }

    @PostMapping("/")
    public ConfirmationToken registerGuest(@RequestBody RegDTO regDTO){
        final ConfirmationToken token = guestRegService.regGuest(Guest
            .builder()
            .firstName(regDTO.getFirstName())
            .lastName(regDTO.getLastName())
            .email(regDTO.getEmail())
            .phone(regDTO.getPhone())
            .password(regDTO.getPassword())
            .build()
        );
        return token;
    }
    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return guestRegService.confirmToken(token);
    }
}
