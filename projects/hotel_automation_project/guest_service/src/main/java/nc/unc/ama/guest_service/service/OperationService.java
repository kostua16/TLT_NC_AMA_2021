package nc.unc.ama.guest_service.service;

import nc.unc.ama.guest_service.entity.Operation;

import java.util.List;

public interface OperationService {

    List<Operation> getAllOperations();

    void saveOperation(Operation operation);

    Operation getOperation(int idOperation);

    void deleteOperation(int idOperation);

}
