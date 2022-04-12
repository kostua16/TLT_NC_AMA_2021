package nc.unc.ama.guest_service.controller;

import java.util.ArrayList;
import java.util.List;
import nc.unc.ama.complaint_handling_service.dto.OperationDTO;
import nc.unc.ama.complaint_handling_service.dto.OperationREST;
import nc.unc.ama.guest_service.entity.Operation;
import nc.unc.ama.guest_service.service.OperationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OperationController {

    private final OperationServiceImpl operationService;

    @Autowired
    public OperationController(OperationServiceImpl operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/")
    public List<OperationDTO> showAllOperations() {
        List<OperationDTO> operationDTO = new ArrayList<>();
        for (Operation operation : operationService.getAllOperations()) {
            operationDTO.add(new OperationDTO(
                operation.getIdOperation(),
                operation.getOperationName(),
                operation.getDescription(),
                operation.getPrice()));
        }
        return operationDTO;
    }

    @GetMapping("/{idOperation}")
    public OperationDTO getOperation(@PathVariable("idOperation") int idOperation) {
        Operation operation = operationService.getOperation(idOperation);
        return new OperationDTO(
            operation.getIdOperation(),
            operation.getOperationName(),
            operation.getDescription(),
            operation.getPrice());
    }

    @PostMapping("/")
    public void addNewOperation(@RequestBody OperationDTO operationDTO) {
        operationService.saveOperation(Operation
            .builder()
            .idOperation(operationDTO.getIdOperation())
            .operationName(operationDTO.getOperationName())
            .description(operationDTO.getDescription())
            .price(operationDTO.getPrice())
            .build()
        );
    }

    @PutMapping("/")
    public Operation updateOperation(@RequestBody Operation operation) {
        operationService.saveOperation(operation);
        return operation;
    }

    @DeleteMapping("/{idOperation}")
    public String deleteOperation(@PathVariable int idOperation) {
        operationService.deleteOperation(idOperation);
        return "Operation with ID = " + idOperation + " was deleted";
    }
}
