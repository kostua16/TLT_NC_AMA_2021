package nc.unc.ama.staff_service.controller;


import nc.unc.ama.common.dto.StaffTypeCreateDTO;
import nc.unc.ama.staff_service.entities.StaffType;
import nc.unc.ama.staff_service.service.StaffTypeService;
import nc.unc.ama.common.dto.StaffTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/staff-types")
public class StaffTypeController {
    private final StaffTypeService staffTypeService;

    @Autowired
    public StaffTypeController(StaffTypeService staffTypeService) {
        this.staffTypeService = staffTypeService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<StaffTypeDTO> createNewStaffType(@RequestBody StaffTypeCreateDTO staffTypeDTO){
        final StaffType staffType =  staffTypeService.createStaffType(StaffType
            .builder()
            .staffTypeName(staffTypeDTO.getStaffTypeName())
            .typeDescription(staffTypeDTO.getTypeDescription())
            .build()
        );
        return ResponseEntity.ok(new StaffTypeDTO(
            staffType.getStaffTypeId(),
            staffType.getStaffTypeName(),
            staffType.getTypeDescription()
        ));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<StaffTypeDTO> getStaffType(@PathVariable("id") Long staffTypeId)
    {
        StaffType newStaffType= staffTypeService.getStaffTypeId(staffTypeId);
        return ResponseEntity.ok(new StaffTypeDTO(
            newStaffType.getStaffTypeId(),
            newStaffType.getStaffTypeName(),
            newStaffType.getTypeDescription()
        ));
    }
    @GetMapping(path = "/")
    public ResponseEntity<List<StaffTypeDTO>> getAllStaffTypes() {
        List<StaffTypeDTO> staffTypeDTOList = new ArrayList<>();
        for (StaffType staffType : staffTypeService.getaAllStaffTypes()) {
            staffTypeDTOList.add(new StaffTypeDTO(
                staffType.getStaffTypeId(),
                staffType.getStaffTypeName(),
                staffType.getTypeDescription()
            ));
        }
        return ResponseEntity.ok(staffTypeDTOList);
    }
    @PutMapping(path = "/{id)")
    public ResponseEntity<StaffTypeDTO> updateStaffType(@PathVariable("id") Long staffTypeId, @RequestBody StaffTypeDTO staffTypeDTO){
        final StaffType staffType = staffTypeService.updateStaffType(StaffType
                .builder()
                .staffTypeId(staffTypeDTO.getStaffTypeId())
                .staffTypeName(staffTypeDTO.getStaffTypeName())
                .typeDescription(staffTypeDTO.getTypeDescription())
                .build(),
            staffTypeId
        );
        return ResponseEntity.ok(new StaffTypeDTO(
            staffType.getStaffTypeId(),
            staffType.getStaffTypeName(),
            staffType.getTypeDescription()
        ));
    }

    @DeleteMapping(path="/{id)")
    public ResponseEntity<String> deleteStaffTypeId(@PathVariable("id") Long staffTypeId){
        staffTypeService.deleteStaffType(staffTypeId);
        return ResponseEntity.ok("StaffType with ID = " + staffTypeId + " was deleted");
    }
}
