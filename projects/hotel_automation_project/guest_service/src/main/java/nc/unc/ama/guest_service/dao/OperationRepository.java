package nc.unc.ama.guest_service.dao;

import nc.unc.ama.guest_service.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
}
