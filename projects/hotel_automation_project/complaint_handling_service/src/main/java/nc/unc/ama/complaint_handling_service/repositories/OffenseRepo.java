package nc.unc.ama.complaint_handling_service.repositories;

import nc.unc.ama.complaint_handling_service.entities.Offense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffenseRepo extends JpaRepository<Offense,Long> {

}
