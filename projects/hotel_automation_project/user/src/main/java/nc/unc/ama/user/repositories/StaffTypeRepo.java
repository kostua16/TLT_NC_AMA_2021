package nc.unc.ama.user.repositories;

import nc.unc.ama.user.entities.StaffType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffTypeRepo extends JpaRepository<StaffType,Long> {
}
