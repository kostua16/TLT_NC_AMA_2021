package nc.unc.ama.complaint_handling_service.repositories;

import nc.unc.ama.complaint_handling_service.entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepo extends JpaRepository<Reviews,Long> {
    @Query("SELECT r FROM Reviews r WHERE r.guestId=?1")
    List<Reviews> findReviewsByGuestId(Long guestId);
}
