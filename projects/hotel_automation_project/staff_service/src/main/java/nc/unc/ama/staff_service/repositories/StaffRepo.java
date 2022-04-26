package nc.unc.ama.staff_service.repositories;

import nc.unc.ama.staff_service.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StaffRepo extends JpaRepository<Staff,Long> {
}
