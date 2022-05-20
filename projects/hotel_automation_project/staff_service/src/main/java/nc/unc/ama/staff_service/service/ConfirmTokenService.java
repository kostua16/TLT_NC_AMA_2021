package nc.unc.ama.staff_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import nc.unc.ama.staff_service.entities.ConfirmationToken;
import nc.unc.ama.staff_service.entities.UserEntity;
import nc.unc.ama.staff_service.repositories.ConfirmTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmTokenService {
    private final ConfirmTokenRepo confirmTokenRepo;

    @Autowired
    public ConfirmTokenService(ConfirmTokenRepo confirmTokenRepo) {
        this.confirmTokenRepo = confirmTokenRepo;
    }

    @Transactional
    public ConfirmationToken generateToken(final UserEntity user) {
        return this.saveConfirmToken(new ConfirmationToken(user));
    }

    @Transactional
    public ConfirmationToken saveConfirmToken(ConfirmationToken confirmationToken) {
        return confirmTokenRepo.save(confirmationToken);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmTokenRepo.findByToken(token);
    }

    public List<ConfirmationToken> getTokens(UserEntity user) {
        return confirmTokenRepo.findByUser(user);
    }

    public List<ConfirmationToken> getActiveTokens(UserEntity user) {
        return confirmTokenRepo.findActiveByUserThatExpireAfter(user, LocalDateTime.now());
    }

    @Transactional
    public int confirmedAt(String token) {
        return confirmTokenRepo.updateConfirmedAt(token, LocalDateTime.now());
    }

}
