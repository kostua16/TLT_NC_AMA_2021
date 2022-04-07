package nc.unc.ama.complaint_handling_service.repositories;

import nc.unc.ama.complaint_handling_service.entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ComplaintRepo extends JpaRepository<Complaint,Long> {
    @Query("SELECT c FROM Complaint c WHERE c.staffMemberId=?1")
    List<Complaint> findComplaintByStaffMemberId(Long staffMemberId);
}
