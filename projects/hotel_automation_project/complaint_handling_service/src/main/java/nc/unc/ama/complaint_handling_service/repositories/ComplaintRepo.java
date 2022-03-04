package nc.unc.ama.complaint_handling_service.repositories;

import nc.unc.ama.complaint_handling_service.entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepo extends JpaRepository<Complaint,Long> {

}
