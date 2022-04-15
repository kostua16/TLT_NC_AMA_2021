package nc.unc.ama.guest_service.dao;

import nc.unc.ama.guest_service.entity.Bills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillsRepository extends JpaRepository<Bills, Integer> {
}
