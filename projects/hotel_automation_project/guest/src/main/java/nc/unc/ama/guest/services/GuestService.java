package nc.unc.ama.guest.services;


import nc.unc.ama.guest.entities.Guest;
import nc.unc.ama.guest.err.GuestNotFoundException;
import nc.unc.ama.guest.repositories.GuestRepo;
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
    public Guest createGuest(Guest guest) {
        return guestRepo.save(guest);
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
    @Transactional
    public void deleteGuest(Long guestId) {
        guestRepo.deleteById(guestId);
    }
}
