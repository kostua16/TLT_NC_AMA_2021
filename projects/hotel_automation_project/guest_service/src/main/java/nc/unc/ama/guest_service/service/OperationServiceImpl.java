package nc.unc.ama.guest_service.service;

import nc.unc.ama.guest_service.dao.OperationRepository;
import nc.unc.ama.guest_service.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OperationServiceImpl{

    private final OperationRepository operationRepo;

    @Autowired
    public OperationServiceImpl(OperationRepository operationRepo) {
        this.operationRepo = operationRepo;
    }


    public List<Operation> getAllOperations() {
        return operationRepo.findAll();
    }

    @Transactional
    public void saveOperation(Operation operation) {
        operationRepo.save(operation);
    }


    public Operation getOperation(int idOperation) {
        Optional<Operation> optional = operationRepo.findById(idOperation);
        return optional.get();
    }

    @Transactional
    public void deleteOperation(int idOperation) {
        operationRepo.deleteById(idOperation);
    }
}
