package nc.unc.ama.staff_service.repositories;

import nc.unc.ama.staff_service.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface StaffRepo extends JpaRepository<Staff, Long> {
    @Query(value = "SELECT * FROM Staff s WHERE s.staffTypeId=?1 order by RAND() limit 1", nativeQuery = true)
    Staff getRandomStaff(Long staffTypeId);
}
