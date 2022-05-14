package nc.unc.ama.guest.repositories;


import nc.unc.ama.guest.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface GuestRepo extends JpaRepository<Guest, Long> {
    Optional<Guest> findByEmail(String email);
    @Transactional
    @Modifying
    @Query("UPDATE Guest g " +
        "SET g.enabled = TRUE WHERE g.email = ?1")
    int enableGuest(String email);
}
