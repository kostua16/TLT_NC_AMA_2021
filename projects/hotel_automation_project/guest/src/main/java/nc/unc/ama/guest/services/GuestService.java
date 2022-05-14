package nc.unc.ama.guest.services;


import nc.unc.ama.guest.entities.ConfirmationToken;
import nc.unc.ama.guest.entities.Guest;
import nc.unc.ama.guest.err.GuestNotFoundException;
import nc.unc.ama.guest.repositories.GuestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class GuestService implements UserDetailsService {
    private final GuestRepo guestRepo;
    final private BCryptPasswordEncoder passwordEncoder;
    private final ConfirmTokenService tokenService;

    @Autowired
    public GuestService(GuestRepo guestRepo, BCryptPasswordEncoder passwordEncoder, ConfirmTokenService tokenService)
    {
        this.guestRepo = guestRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Transactional
    public ConfirmationToken createGuest(Guest guest) {
        boolean guestExists = guestRepo
            .findByEmail(guest.getEmail())
            .isPresent();
        if(guestExists){
            throw new IllegalStateException("email alredy taken");
        }
        String encodePass = passwordEncoder.encode(guest.getPassword());
        guest.setPassword(encodePass);
        guestRepo.save(guest);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(10),
            guest
        );
        return tokenService.saveConfirmToken(confirmationToken);
    }

    public Guest getGuest(Long guestId) {
        return guestRepo.findById(guestId).orElseThrow(()-> new GuestNotFoundException(guestId));
    }

    public List<Guest> getAllGuests() {
        return guestRepo.findAll();
    }

    @Transactional
    public Guest updateGuest(Guest guestUpd, Long guestId) {
        guestUpd.setGuestId(guestId);
        return guestRepo.save(guestUpd);
    }
    @Transactional
    public void deleteGuest(Long guestId) {
         guestRepo.deleteById(guestId);
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        return guestRepo.findByEmail(email)
            .orElseThrow(()-> new UsernameNotFoundException(String.format("Guest with email %s not found",email)));
    }
    public int enableGuest(String email) {
        return guestRepo.enableGuest(email);
    }
}
