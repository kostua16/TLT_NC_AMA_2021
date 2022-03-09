package nc.unc.ama.booking_service.services;

import nc.unc.ama.booking_service.entities.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final nc.unc.ama.booking_service.repositories.RegistrationRepo registrationRepo;

    @Autowired
    public RegistrationService(nc.unc.ama.booking_service.repositories.RegistrationRepo registrationRepo)
    {
        this.registrationRepo = registrationRepo;
    }

    public Registration getRegisteredUsers (String password, String username, Long registrationId)
    {
        return registrationRepo.findById(registrationId).get();
    }
}
