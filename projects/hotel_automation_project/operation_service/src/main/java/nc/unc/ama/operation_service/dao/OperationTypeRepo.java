package nc.unc.ama.operation_service.dao;

import nc.unc.ama.operation_service.entity.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationTypeRepo extends JpaRepository<OperationType,Long> {

    List<OperationType> findAllByStaffTypeId(Long staffTypeId);

}
