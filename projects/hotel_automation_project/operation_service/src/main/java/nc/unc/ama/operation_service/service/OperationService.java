package nc.unc.ama.operation_service.service;

import nc.unc.ama.operation_service.dao.OperationRepository;
import nc.unc.ama.operation_service.entity.Operation;
import nc.unc.ama.operation_service.err.OperationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OperationService{

    private final OperationRepository operationRepo;

    @Autowired
    public OperationService(OperationRepository operationRepo) {
        this.operationRepo = operationRepo;
    }


    public List<Operation> getAllOperations() {
        return operationRepo.findAll();
    }

    @Transactional
    public Operation saveOperation(Operation operation) {
        return operationRepo.save(operation);
    }
    @Transactional
    public Operation updateOperation(Operation operationUpd, Long operationId) {
        operationUpd.setIdOperation(operationId);
        return operationRepo.save(operationUpd);
    }
    public Operation getOperation(Long idOperation) {
        return operationRepo.findById(idOperation).orElseThrow(()->new OperationNotFoundException(idOperation));
    }

    @Transactional
    public void deleteOperation(Long idOperation) {
        operationRepo.deleteById(idOperation);
    }
}
