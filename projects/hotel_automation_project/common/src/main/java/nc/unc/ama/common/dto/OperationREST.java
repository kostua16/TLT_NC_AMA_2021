package nc.unc.ama.common.dto;

import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "guest_service", path = "/api/operations")
@ConditionalOnProperty(prefix = "app.clients", name = "operationsApi")
public interface OperationREST {

    @GetMapping("/api/operations/")
    List<OperationDTO> getAllOperations();

    @PostMapping("/api/operations/{idOperation}")
    void saveOperation(OperationDTO operation);

    @GetMapping("/api/operations/{idOperation}")
    OperationDTO getOperation(int idOperation);

    @DeleteMapping("/api/operations/{idOperation}")
    void deleteOperation(int idOperation);

}
