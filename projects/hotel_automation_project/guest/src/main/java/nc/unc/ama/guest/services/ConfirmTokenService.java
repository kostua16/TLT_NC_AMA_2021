package nc.unc.ama.guest.services;

import nc.unc.ama.guest.entities.ConfirmationToken;
import nc.unc.ama.guest.repositories.ConfirmTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmTokenService {
    private final ConfirmTokenRepo confirmTokenRepo;

    @Autowired
    public ConfirmTokenService(ConfirmTokenRepo confirmTokenRepo) {
        this.confirmTokenRepo = confirmTokenRepo;
    }

    public ConfirmationToken saveConfirmToken(ConfirmationToken confirmationToken) {
        return confirmTokenRepo.save(confirmationToken);
    }
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmTokenRepo.findByToken(token);
    }
    public int confirmedAt(String token) {
        return confirmTokenRepo.updateConfirmedAt(
            token, LocalDateTime.now());
    }

}
