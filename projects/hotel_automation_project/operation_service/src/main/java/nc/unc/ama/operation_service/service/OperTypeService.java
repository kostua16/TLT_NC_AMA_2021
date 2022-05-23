package nc.unc.ama.operation_service.service;

import nc.unc.ama.operation_service.dao.OperationTypeRepo;
import nc.unc.ama.operation_service.entity.OperationType;
import nc.unc.ama.operation_service.err.OperTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
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

    public List<OperationType> getOperationTypeByStaffType(Long staffTypeId) {
        return operationTypeRepo.findAllByStaffTypeId(staffTypeId);
    }
    @PostConstruct
    public void initialize(){
            List<OperationType> operationTypes = new ArrayList<>();
            Collections.addAll(operationTypes,
                OperationType
                    .builder()
                    .idOperType(1L)
                    .staffTypeId(1L)
                    .description("Room cleaning")
                    .build(),
                OperationType
                    .builder()
                    .idOperType(2L)
                    .staffTypeId(1L)
                    .description("Change of bed linen")
                    .build(),
                OperationType
                    .builder()
                    .idOperType(3L)
                    .staffTypeId(1L)
                    .description("Change of towels")
                    .build(),
                OperationType
                    .builder()
                    .idOperType(4L)
                    .staffTypeId(2L)
                    .description("Fixing plumbing")
                    .build(),
                OperationType
                    .builder()
                    .idOperType(5L)
                    .staffTypeId(2L)
                    .description("Electronics repair")
                    .build()
            );
            operationTypeRepo.saveAll(operationTypes);
        }


}
