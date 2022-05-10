package nc.unc.ama.common.dto;

import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "operation_service", path = "/api/operations")
@ConditionalOnProperty(prefix = "app.clients", name = "operationsApi")
public interface OperationREST {

    @GetMapping("/")
    ResponseEntity<List<OperationDTO>> showAllOperations();
    @GetMapping("/{id}")
    ResponseEntity<OperationDTO> getOperation(@PathVariable("id") Long idOperation);
    @PostMapping("/")
    ResponseEntity<OperationDTO> addNewOperation(@RequestBody OperationCreateDTO operCreateDTO);
    @PutMapping("/{id}")
    ResponseEntity<OperationDTO> updateOperation(@PathVariable("id") Long operationId,@RequestBody OperationDTO operationDTO);
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteOperation(@PathVariable("id") Long idOperation);

}
