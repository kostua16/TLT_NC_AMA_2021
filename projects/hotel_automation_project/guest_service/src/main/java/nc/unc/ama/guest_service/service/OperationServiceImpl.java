package nc.unc.ama.guest_service.service;

import nc.unc.ama.guest_service.dao.OperationRepository;
import nc.unc.ama.guest_service.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationServiceImpl implements OperationService{

    @Autowired
    private OperationRepository operationRepo;

    @Override
    public List<Operation> getAllOperations() {
        return operationRepo.findAll();
    }

    @Override
    public void saveOperation(Operation operation) {
        operationRepo.save(operation);
    }

    @Override
    public Operation getOperation(int idOperation) {
        Operation operation = null;
        Optional<Operation> optional = operationRepo.findById(idOperation);
        if (optional.isPresent()){
            operation = optional.get();
        }
        return operation;
    }

    @Override
    public void deleteOperation(int idOperation) {
        operationRepo.deleteById(idOperation);
    }
}
