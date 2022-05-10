package nc.unc.ama.guest.controllers;


import nc.unc.ama.common.dto.GuestCreationDTO;
import nc.unc.ama.common.dto.GuestDTO;
import nc.unc.ama.common.dto.GuestREST;
import nc.unc.ama.guest.entities.Guest;
import nc.unc.ama.guest.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/guests")
public class GuestController implements GuestREST {

    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping(path = "/")
    @Override
    public ResponseEntity<GuestDTO> guestReg(@RequestBody GuestCreationDTO guestDTO){
        final Guest guest = guestService.createGuest(Guest
            .builder()
            .guestFName(guestDTO.getGuestFName())
            .guestLName(guestDTO.getGuestLName())
            .guestEmail(guestDTO.getGuestEmail())
            .guestPhone(guestDTO.getGuestPhone())
            .build()
        );
        return ResponseEntity.ok(
            new GuestDTO(
                guest.getGuestFName(),
                guest.getGuestLName(),
                guest.getGuestEmail(),
                guest.getGuestPhone(),
                guest.getGuestId()
            )
        );
    }

    @GetMapping(path = "/{id}")
    @Override
    public ResponseEntity<GuestDTO> getGuest(@PathVariable("id") Long guestId) {
        final Guest guest = guestService.getGuest(guestId);
        return ResponseEntity.ok(new GuestDTO(
            guest.getGuestFName(),
            guest.getGuestLName(),
            guest.getGuestEmail(),
            guest.getGuestPhone(),
            guest.getGuestId()
        ));
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<GuestDTO>> getAllGuests() {
        List<GuestDTO> guestDTOList = new ArrayList<>();
        for (Guest guest : guestService.getAllGuests()) {
            guestDTOList.add(new GuestDTO(
                guest.getGuestFName(),
                guest.getGuestLName(),
                guest.getGuestEmail(),
                guest.getGuestPhone(),
                guest.getGuestId()
            ));
        }
        return ResponseEntity.ok(guestDTOList);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<GuestDTO> updateGuest(@PathVariable("id") Long guestId, @RequestBody GuestDTO guestDTO){
        final Guest guest = guestService.updateGuest(Guest
                .builder()
                .guestId(guestDTO.getGuestId())
                .guestFName(guestDTO.getGuestFName())
                .guestLName(guestDTO.getGuestLName())
                .guestEmail(guestDTO.getGuestEmail())
                .guestPhone(guestDTO.getGuestPhone())
                .build(),
            guestId
        );
        return ResponseEntity.ok(
            new GuestDTO(
                guest.getGuestFName(),
                guest.getGuestLName(),
                guest.getGuestEmail(),
                guest.getGuestPhone(),
                guest.getGuestId()
            )
        );
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<HttpStatus> deleteGuest(@PathVariable("id") Long guestId){
        guestService.deleteGuest(guestId);
        return ResponseEntity.accepted().build();
    }
}
