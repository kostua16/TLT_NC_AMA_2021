package nc.unc.ama.complaint_handling_service.repositories;

import java.util.List;
import java.util.UUID;
import nc.unc.ama.complaint_handling_service.entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepo extends JpaRepository<Reviews, Long> {

    @Query("SELECT r FROM Reviews r WHERE r.guestId=?1")
    List<Reviews> findReviewsByGuestId(UUID guestId);
}
