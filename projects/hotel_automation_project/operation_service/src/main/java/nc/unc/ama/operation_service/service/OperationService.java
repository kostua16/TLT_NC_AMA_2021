package nc.unc.ama.operation_service.service;

import nc.unc.ama.common.dto.StaffDTO;
import nc.unc.ama.common.dto.StaffREST;
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
    private final StaffREST staffREST;

    @Autowired
    public OperationService(OperationRepository operationRepo, StaffREST staffREST) {
        this.operationRepo = operationRepo;
        this.staffREST = staffREST;
    }


    public List<Operation> getAllOperations() {
        return operationRepo.findAll();
    }

    @Transactional
    public Operation saveOperation(Operation operation) {
        StaffDTO staff = staffREST.getRandomStaff(operation.getOperationTypeId());
        operation.setStaffId(staff.getStaffId());
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

    @Transactional
    public Operation operationDone(Long operationId){
        Operation operation = operationRepo.findById(operationId).orElseThrow(()->new OperationNotFoundException(operationId));
        operation.setStatus(true);
        return operationRepo.save(operation);
    }
}
