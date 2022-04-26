package nc.unc.ama.booking_service.services;

import nc.unc.ama.booking_service.entities.Guest;
import nc.unc.ama.booking_service.err.GuestNotFoundException;
import nc.unc.ama.booking_service.repositories.GuestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GuestService {
    private final GuestRepo guestRepo;

    @Autowired
    public GuestService(GuestRepo guestRepo)
    {
        this.guestRepo = guestRepo;
    }

    @Transactional
    public void createGuest(Guest guest) {
        guestRepo.save(guest);
    }

    public Guest getGuest(Long guestId) {
        return guestRepo.findById(guestId).orElseThrow(()-> new GuestNotFoundException(guestId));
    }

    public List<Guest> getAllGuests() {
        return guestRepo.findAll();
    }

    @Transactional
    public void updateGuest(Guest guestUpd, Long guestId) {
        guestRepo.save(guestUpd);
        guestRepo.findById(guestId);

    }

    public void deleteGuest(Long guestId) {
        guestRepo.deleteById(guestId);
    }
}
