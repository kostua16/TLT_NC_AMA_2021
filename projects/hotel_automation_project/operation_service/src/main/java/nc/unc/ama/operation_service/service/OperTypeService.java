package nc.unc.ama.operation_service.service;

import nc.unc.ama.operation_service.dao.OperationTypeRepo;
import nc.unc.ama.operation_service.entity.OperationType;
import nc.unc.ama.operation_service.err.OperTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperTypeService {

    private final OperationTypeRepo operationTypeRepo;

    @Autowired
    public OperTypeService(OperationTypeRepo operationTypeRepo) {
        this.operationTypeRepo = operationTypeRepo;
    }

    public List<OperationType> getAllOperTypes() {
        return operationTypeRepo.findAll();
    }

    public OperationType getOperationType(Long operTypeId) {
        return operationTypeRepo.findById(operTypeId).orElseThrow(()-> new OperTypeNotFoundException(operTypeId));
    }

    public OperationType addOperType(OperationType operationType) {
        return operationTypeRepo.save(operationType);
    }

    public OperationType updateOperType(OperationType operationTypeUpd, Long operTypeId) {
        operationTypeUpd.setIdOperType(operTypeId);
        return operationTypeRepo.save(operationTypeUpd);
    }

    public void deleteOperationType(Long operTypeId) {
        operationTypeRepo.deleteById(operTypeId);
    }
}
