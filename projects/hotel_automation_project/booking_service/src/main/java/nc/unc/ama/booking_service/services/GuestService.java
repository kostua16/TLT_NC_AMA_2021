package nc.unc.ama.booking_service.services;

import nc.unc.ama.booking_service.entities.Guest;
import nc.unc.ama.booking_service.repositories.GuestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {
    private final GuestRepo guestRepo;

    @Autowired
    public GuestService(GuestRepo guestRepo)
    {
        this.guestRepo = guestRepo;
    }

    public Guest getRegisteredUsers (String password, String username, Long registrationId)
    {
        return guestRepo.findById(registrationId).orElseThrow(() -> new NullPointerException());
    }

    public void createGuest(Guest guest) {
        guestRepo.save(guest);
    }

    public Guest getGuest(Long guestId) {
        return guestRepo.findById(guestId).get();
    }

    public List<Guest> getAllGuests() {
        return guestRepo.findAll();
    }

    public void updateGuest(Guest guestUpd, Long guestId) {
        guestRepo.findById(guestId)
            .map(guest -> {
                guest.setGuestFName(guestUpd.getGuestFName());
                guest.setGuestLName(guestUpd.getGuestLName());
                guest.setGuestEmail(guestUpd.getGuestEmail());
                guest.setGuestPhone(guestUpd.getGuestPhone());
                return guestRepo.save(guest);
            })
            .orElseGet(() -> {
                guestUpd.setGuestId(guestId);
                return guestRepo.save(guestUpd);
            });
    }

    public void deleteGuest(Long guestId) {
        guestRepo.deleteById(guestId);
    }
}
