package nc.unc.ama.booking_service.repositories;

import nc.unc.ama.booking_service.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepo extends JpaRepository<Guest, Long> {
}
