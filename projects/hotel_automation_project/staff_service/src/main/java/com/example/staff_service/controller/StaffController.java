package com.example.staff_service.controller;


import com.example.staff_service.entities.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.staff_service.service.StaffService;

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
    public ResponseEntity<Iterable<Staff>> getAllStaff() { return ResponseEntity.ok(staffService.getUsers());}

    @PostMapping("/create")
    public ResponseEntity<Staff> createNewStaff(@RequestBody Staff staff) { return ResponseEntity.ok(staffService.createStaff(staff));}

    @DeleteMapping("/delete/{staffId}")
    public ResponseEntity deleteStaffById(@PathVariable ("staffId") int staffId )
    {
        staffService.deleteStaffById(staffId);
        return ResponseEntity.accepted().build();
    }
}
