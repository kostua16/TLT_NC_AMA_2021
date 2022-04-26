package nc.unc.ama.staff_service.controller;


import nc.unc.ama.complaint_handling_service.dto.StaffDTO;
import nc.unc.ama.staff_service.entities.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nc.unc.ama.staff_service.service.StaffService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController
{

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService)
    {
        this.staffService = staffService;
    }

    @GetMapping
    public ResponseEntity<List<StaffDTO>> getAllStaff() {
        List<StaffDTO> staffDTOList = new ArrayList<>();
        for (Staff staff : staffService.getAllStaff()) {
            staffDTOList.add(new StaffDTO(
                staff.getStaffId(),
                staff.getStaffName(),
                staff.getStaffLastName(),
                staff.getStaffRating(),
                staff.getStaffSalary(),
                staff.getStaffTypeId()));
        }
        return ResponseEntity.ok(staffDTOList);
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> createNewStaff(@RequestBody StaffDTO staffDTO) {
        staffService.createStaff(Staff
            .builder()
            .staffId(staffDTO.getStaffId())
            .staffName(staffDTO.getStaffName())
            .staffLastName(staffDTO.getStaffLastName())
            .staffRating(staffDTO.getStaffRating())
            .staffSalary(staffDTO.getStaffSalary())
            .staffTypeId(staffDTO.getStaffTypeId())
            .build()
        );
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{staffId}")
    public ResponseEntity<HttpStatus>  deleteStaffById(@PathVariable ("staffId") Long staffId )
    {
        staffService.deleteStaffById(staffId);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/staffId}")
    public ResponseEntity<StaffDTO> getStaff(@PathVariable ("staffId") Long staffId){
        Staff newStaff = staffService.getStaff(staffId);
        return ResponseEntity.ok(new StaffDTO(
            newStaff.getStaffId(),
            newStaff.getStaffName(),
            newStaff.getStaffLastName(),
            newStaff.getStaffRating(),
            newStaff.getStaffSalary(),
            newStaff.getStaffTypeId()));
    }

    @PutMapping("/{staffId}")
    public ResponseEntity<HttpStatus> updateStaff(@PathVariable ("staffId") Long staffId, @RequestBody StaffDTO staffDTO){
        staffService.updateStaff(Staff
                .builder()
                .staffId(staffDTO.getStaffId())
                .staffName(staffDTO.getStaffName())
                .staffLastName(staffDTO.getStaffLastName())
                .staffRating(staffDTO.getStaffRating())
                .staffSalary(staffDTO.getStaffSalary())
                .staffTypeId(staffDTO.getStaffTypeId())
                .build(),
            staffId
        );
        return ResponseEntity.noContent().build();
    }
}
