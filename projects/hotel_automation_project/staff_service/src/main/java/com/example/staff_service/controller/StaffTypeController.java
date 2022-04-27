package com.example.staff_service.controller;


import com.example.staff_service.entities.StaffType;
import com.example.staff_service.service.StaffTypeService;
import nc.unc.ama.complaint_handling_service.dto.StaffTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/staff-type")
public class StaffTypeController {
    private final StaffTypeService staffTypeService;

    @Autowired
    public StaffTypeController(StaffTypeService staffTypeService) {
        this.staffTypeService = staffTypeService;
    }

    @PostMapping(path = "/")
    public void createNewStaffType(@RequestBody StaffTypeDTO staffTypeDTO){
        staffTypeService.createStaffType(StaffType
            .builder()
            .staffTypeId(staffTypeDTO.getStaffTypeId())
            .staffTypeName(staffTypeDTO.getStaffTypeName())
            .typeDescription(staffTypeDTO.getTypeDescription())
            .build()
        );
    }

    @GetMapping(path = "{staffTypeId}")
    public StaffTypeDTO getStaffType(@PathVariable("staffTypeId") Long staffTypeId)
    {
        StaffType newStaffType= staffTypeService.getStaffTypeId(staffTypeId);
        return new StaffTypeDTO(
            newStaffType.getStaffTypeId(),
            newStaffType.getStaffTypeName(),
            newStaffType.getTypeDescription()
        );
    }
    @GetMapping(path = "/")
    public List<StaffTypeDTO> getAllStaffTypes() {
        List<StaffTypeDTO> staffTypeDTOList = new ArrayList<>();
        for (StaffType staffType : staffTypeService.getaAllStaffTypes()) {
            staffTypeDTOList.add(new StaffTypeDTO(
                staffType.getStaffTypeId(),
                staffType.getStaffTypeName(),
                staffType.getTypeDescription()
            ));
        }
        return staffTypeDTOList;
    }
    @PutMapping(path = "/{staffTypeId)")
    public void updateStaffType(@PathVariable("staffTypeId") Long staffTypeId, @RequestBody StaffTypeDTO staffTypeDTO){
        staffTypeService.updateStaffType(StaffType
                .builder()
                .staffTypeId(staffTypeDTO.getStaffTypeId())
                .staffTypeName(staffTypeDTO.getStaffTypeName())
                .typeDescription(staffTypeDTO.getTypeDescription())
                .build(),
            staffTypeId
        );
    }

    @DeleteMapping(path="/{staffTypeId)")
    public void deleteStaffTypeId(@PathVariable("staffTypeId") Long staffTypeId){
        staffTypeService.deleteStaffType(staffTypeId);
    }
}
