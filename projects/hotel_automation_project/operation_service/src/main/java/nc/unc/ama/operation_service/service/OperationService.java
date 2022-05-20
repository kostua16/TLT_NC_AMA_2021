package nc.unc.ama.operation_service.service;

import java.util.List;
import nc.unc.ama.common.dto.UserInfoDTO;
import nc.unc.ama.common.dto.UsersREST;
import nc.unc.ama.operation_service.dao.OperationRepository;
import nc.unc.ama.operation_service.entity.Operation;
import nc.unc.ama.operation_service.err.OperationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationService {

    private final OperationRepository operationRepo;

    private final UsersREST usersREST;

    @Autowired
    public OperationService(OperationRepository operationRepo, UsersREST usersREST) {
        this.operationRepo = operationRepo;
        this.usersREST = usersREST;
    }


    public List<Operation> getAllOperations() {
        return operationRepo.findAll();
    }

    @Transactional
    public Operation saveOperation(Operation operation) {
        final UserInfoDTO staffMember = usersREST.listStaff().getBody().iterator().next();
        operation.setStaffId(staffMember.getId());
        return operationRepo.save(operation);
    }

    @Transactional
    public Operation updateOperation(Operation operationUpd, Long operationId) {
        operationUpd.setIdOperation(operationId);
        return operationRepo.save(operationUpd);
    }

    public Operation getOperation(Long idOperation) {
        return operationRepo.findById(idOperation).orElseThrow(() -> new OperationNotFoundException(idOperation));
    }

    @Transactional
    public void deleteOperation(Long idOperation) {
        operationRepo.deleteById(idOperation);
    }

    @Transactional
    public Operation operationDone(Long operationId) {
        Operation operation =
            operationRepo.findById(operationId).orElseThrow(() -> new OperationNotFoundException(operationId));
        operation.setStatus(true);
        return operationRepo.save(operation);
    }
}
