package nc.unc.ama.booking_service.services;

import nc.unc.ama.booking_service.entities.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nc.unc.ama.booking_service.repositories.RegistrationRepo;

@Service
public class RegistrationService {
    private final RegistrationRepo registrationRepo;

    @Autowired
    public RegistrationService(RegistrationRepo registrationRepo)
    {
        this.registrationRepo = registrationRepo;
    }

    public Registration getRegisteredUsers (String password, String username, Long registrationId)
    {
        return registrationRepo.findById(registrationId).orElseThrow(() -> new NullPointerException());
    }
}
