package nc.unc.ama.operation_service.controller;

import java.util.ArrayList;
import java.util.List;

import nc.unc.ama.common.dto.OperationCreateDTO;
import nc.unc.ama.common.dto.OperationDTO;
import nc.unc.ama.common.dto.OperationREST;
import nc.unc.ama.operation_service.entity.Operation;
import nc.unc.ama.operation_service.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/operations")
public class OperationController implements OperationREST {

    private final OperationService operationService;

    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<OperationDTO>> showAllOperations() {
        List<OperationDTO> operationDTOList = new ArrayList<>();
        for (Operation operation : operationService.getAllOperations()) {
            operationDTOList.add(new OperationDTO(
                operation.getIdOperation(),
                operation.getOperationName(),
                operation.getDescription(),
                operation.getPrice()));
        }
        return ResponseEntity.ok(operationDTOList);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<OperationDTO> getOperation(@PathVariable("id") Long idOperation) {
        Operation operation = operationService.getOperation(idOperation);
        return ResponseEntity.ok(new OperationDTO(
            operation.getIdOperation(),
            operation.getOperationName(),
            operation.getDescription(),
            operation.getPrice()));
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<OperationDTO> addNewOperation(@RequestBody OperationCreateDTO operCreateDTO) {
        final Operation operation = operationService.saveOperation(Operation
            .builder()
            .operationName(operCreateDTO.getOperationName())
            .description(operCreateDTO.getDescription())
            .price(operCreateDTO.getPrice())
            .build());
        return ResponseEntity.ok(new OperationDTO(
            operation.getIdOperation(),
            operation.getOperationName(),
            operation.getDescription(),
            operation.getPrice()));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<OperationDTO> updateOperation(@PathVariable("id") Long operationId,@RequestBody OperationDTO operationDTO) {
       final Operation operation = operationService.updateOperation(Operation
                .builder()
                .idOperation(operationDTO.getIdOperation())
                .operationName(operationDTO.getOperationName())
                .description(operationDTO.getDescription())
                .price(operationDTO.getPrice())
                .build(),
                operationId
        );
        return ResponseEntity.ok(new OperationDTO(
            operation.getIdOperation(),
            operation.getOperationName(),
            operation.getDescription(),
            operation.getPrice()));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOperation(@PathVariable("id") Long idOperation) {
        operationService.deleteOperation(idOperation);
        return ResponseEntity.ok("Operation with ID = " + idOperation + " was deleted");
    }
}
