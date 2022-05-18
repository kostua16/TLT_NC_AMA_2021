package nc.unc.ama.staff_service.controller;


import nc.unc.ama.common.dto.StaffCreateDTO;
import nc.unc.ama.common.dto.StaffDTO;
import nc.unc.ama.common.dto.StaffREST;
import nc.unc.ama.staff_service.entities.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nc.unc.ama.staff_service.service.StaffService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController implements StaffREST
{

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService)
    {
        this.staffService = staffService;
    }


    @GetMapping("/")
    @Override
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
    @Override
    public ResponseEntity<StaffDTO> createNewStaff(@RequestBody StaffCreateDTO staffDTO) {
        final Staff newMember = staffService.createStaff(Staff
            .builder()
            .staffName(staffDTO.getStaffName())
            .staffLastName(staffDTO.getStaffLastName())
            .staffRating(staffDTO.getStaffRating())
            .staffSalary(staffDTO.getStaffSalary())
            .staffTypeId(staffDTO.getStaffTypeId())
            .build()
        );
        return ResponseEntity.ok(
            new StaffDTO(
                newMember.getStaffId(),
                newMember.getStaffName(),
                newMember.getStaffLastName(),
                newMember.getStaffRating(),
                newMember.getStaffSalary(),
                newMember.getStaffTypeId())
        );
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<HttpStatus> deleteStaffById(@PathVariable ("id") Long staffId )
    {
        staffService.deleteStaffById(staffId);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<StaffDTO> getStaff(@PathVariable ("id") Long staffId){
        Staff newStaff = staffService.getStaff(staffId);
        return ResponseEntity.ok(new StaffDTO(
            newStaff.getStaffId(),
            newStaff.getStaffName(),
            newStaff.getStaffLastName(),
            newStaff.getStaffRating(),
            newStaff.getStaffSalary(),
            newStaff.getStaffTypeId()));
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<StaffDTO> updateStaff(@PathVariable ("id") Long staffId, @RequestBody StaffDTO staffDTO){
        final Staff staff = staffService.updateStaff(Staff
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
        return ResponseEntity.ok(new StaffDTO(
            staff.getStaffId(),
            staff.getStaffName(),
            staff.getStaffLastName(),
            staff.getStaffRating(),
            staff.getStaffSalary(),
            staff.getStaffTypeId()));
    }

    @PostMapping("/changeRating/{id}")
    @Override
    public ResponseEntity<HttpStatus> changeRating(@PathVariable ("id") Long staffId,
                                                   @RequestParam (required = false, name = "staffRating") Integer points,
                                                   @RequestParam (required = false, name = "plusOrSub") Boolean plusOrSub
                                                   ){
        staffService.changeRating(staffId,points,plusOrSub);
        return ResponseEntity.accepted().build();
    }
    @Override
    @GetMapping("/get-random")
    public StaffDTO getRandomStaff(@RequestBody Long staffTypeId){
        Staff staff = staffService.getRandomStaff(staffTypeId);
        return new StaffDTO(
            staff.getStaffId(),
            staff.getStaffName(),
            staff.getStaffLastName(),
            staff.getStaffRating(),
            staff.getStaffSalary(),
            staff.getStaffTypeId());
    }
}
