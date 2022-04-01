package nc.unc.ama.guest_service.controller;

import nc.unc.ama.guest_service.entity.Operation;
import nc.unc.ama.guest_service.service.OperationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operations")
public class OperationController {

    private final OperationServiceImpl operationService;

    @Autowired
    public OperationController(OperationServiceImpl operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/")
    public List<Operation> showAllOperations(){
        return operationService.getAllOperations();
    }

    @GetMapping("/{idOperation}")
    public Operation getOperation(@PathVariable int idOperation){
        return operationService.getOperation(idOperation);
    }

    @PostMapping("/")
    public Operation addNewOperation(@RequestBody Operation operation){
        operationService.saveOperation(operation);
        return operation;
    }

    @PutMapping("/")
    public Operation updateOperation(@RequestBody Operation operation){
        operationService.saveOperation(operation);
        return operation;
    }

    @DeleteMapping("/{idOperation}")
    public String deleteOperation(@PathVariable int idOperation){
        operationService.deleteOperation(idOperation);
        return "Operation with ID = " + idOperation + " was deleted";
    }
}
