package nc.unc.ama.complaint_handling_service.dto;

import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(name = "guest_service", path = "/api/operations")
public interface OperationREST {

    List<OperationDTO> getAllOperations();

    void saveOperation(OperationDTO operation);

    OperationDTO getOperation(int idOperation);

    void deleteOperation(int idOperation);
}
