package nc.unc.ama.operation_service.dao;

import nc.unc.ama.operation_service.entity.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepo extends JpaRepository<OperationType,Long> {
}
