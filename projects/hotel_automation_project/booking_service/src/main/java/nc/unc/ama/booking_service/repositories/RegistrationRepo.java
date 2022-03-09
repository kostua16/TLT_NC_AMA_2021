package nc.unc.ama.booking_service.repositories;

import nc.unc.ama.booking_service.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepo extends JpaRepository<Registration, Long> {
}
