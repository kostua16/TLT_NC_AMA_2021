package nc.unc.ama.operation_service.controller;

import nc.unc.ama.common.dto.OperTypeCreateDTO;
import nc.unc.ama.common.dto.OperTypeDTO;
import nc.unc.ama.operation_service.entity.OperationType;
import nc.unc.ama.operation_service.service.OperTypeService;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/operation-type")
public class OperTypeController {

    private final OperTypeService operTypeService;

    @Autowired
    public OperTypeController(OperTypeService operTypeService) {
        this.operTypeService = operTypeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<OperTypeDTO>> showAllOperationTypes() {
        List<OperTypeDTO> operTypeDTOList = new ArrayList<>();
        for (OperationType operationType : operTypeService.getAllOperTypes()) {
            operTypeDTOList.add(new OperTypeDTO(
                operationType.getIdOperType(),
                operationType.getStaffId(),
                operationType.getDescription()
            ));
        }
        return ResponseEntity.ok(operTypeDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperTypeDTO> getOperationType(@PathVariable("id") Long operTypeId) {
        OperationType operationType = operTypeService.getOperationType(operTypeId);
        return ResponseEntity.ok(new OperTypeDTO(
            operationType.getIdOperType(),
            operationType.getStaffId(),
            operationType.getDescription()
        ));
    }

    @PostMapping("/")
    public ResponseEntity<OperTypeDTO> addNewOperationType(@RequestBody OperTypeCreateDTO operTypeCreateDTO) {
        final OperationType operationType = operTypeService.addOperType(OperationType
            .builder()
            .staffId(operTypeCreateDTO.getStaffId())
            .description(operTypeCreateDTO.getDescription())
            .build());
        return ResponseEntity.ok(new OperTypeDTO(
            operationType.getIdOperType(),
            operationType.getStaffId(),
            operationType.getDescription()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperTypeDTO> updateOperationType(@PathVariable("id") Long operTypeId,@RequestBody OperTypeDTO operTypeDTO) {
        final OperationType operationType = operTypeService.updateOperType(OperationType
                .builder()
                .idOperType(operTypeDTO.getIdOperType())
                .staffId(operTypeDTO.getStaffId())
                .description(operTypeDTO.getDescription())
                .build(),
            operTypeId
        );
        return ResponseEntity.ok(new OperTypeDTO(
            operationType.getIdOperType(),
            operationType.getStaffId(),
            operationType.getDescription()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOperationType(@PathVariable("id") Long operTypeId) {
        operTypeService.deleteOperationType(operTypeId);
        return ResponseEntity.ok("Operation with ID = " + operTypeId + " was deleted");
    }
}
