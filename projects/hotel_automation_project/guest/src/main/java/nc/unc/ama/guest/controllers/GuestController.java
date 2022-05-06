package nc.unc.ama.guest.controllers;


import nc.unc.ama.complaint_handling_service.dto.GuestDTO;
import nc.unc.ama.guest.entities.Guest;
import nc.unc.ama.guest.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/guest")
public class GuestController {

    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping(path = "/")
    public void guestReg(@RequestBody GuestDTO guestDTO){
        guestService.createGuest(Guest
            .builder()
            .guestId(guestDTO.getGuestId())
            .guestFName(guestDTO.getGuestFName())
            .guestLName(guestDTO.getGuestLName())
            .guestEmail(guestDTO.getGuestEmail())
            .guestPhone(guestDTO.getGuestPhone())
            .build()
        );
    }

    @GetMapping(path = "/{guestId}")
    public GuestDTO getGuest(@PathVariable("guestId") Long guestId) {
        Guest newGuest = guestService.getGuest(guestId);
        return new GuestDTO(
            newGuest.getGuestId(),
            newGuest.getGuestFName(),
            newGuest.getGuestLName(),
            newGuest.getGuestEmail(),
            newGuest.getGuestPhone()
        );
    }

    @GetMapping(path = "/")
    public List<GuestDTO> getAllGuests() {
        List<GuestDTO> guestDTOList = new ArrayList<>();
        for (Guest guest : guestService.getAllGuests()) {
            guestDTOList.add(new GuestDTO(
                guest.getGuestId(),
                guest.getGuestFName(),
                guest.getGuestLName(),
                guest.getGuestEmail(),
                guest.getGuestPhone()
            ));
        }
        return guestDTOList;
    }

    @PutMapping(path = "/{guestId}")
    public void updateGuest(@PathVariable("guestId") Long guestId, @RequestBody GuestDTO guestDTO){
        guestService.updateGuest(Guest
                .builder()
                .guestId(guestDTO.getGuestId())
                .guestFName(guestDTO.getGuestFName())
                .guestLName(guestDTO.getGuestLName())
                .guestEmail(guestDTO.getGuestEmail())
                .guestPhone(guestDTO.getGuestPhone())
                .build(),
            guestId
        );
    }

    @DeleteMapping(path="/{guestId}")
    public void deleteGuest(@PathVariable("guestId") Long guestId){
        guestService.deleteGuest(guestId);
    }
}
