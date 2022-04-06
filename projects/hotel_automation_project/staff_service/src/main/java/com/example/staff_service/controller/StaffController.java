package com.example.staff_service.controller;


import com.example.staff_service.entities.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.staff_service.service.StaffService;

import java.util.Optional;

@RestController
@RequestMapping("/staff")
public class StaffController
{


    @Autowired
    public StaffController(StaffService staffService)
    {
        this.staffService = staffService;
    }

    private final StaffService staffService;

    @GetMapping
    public ResponseEntity<Iterable<Staff>> getAllStaff() { return ResponseEntity.ok(staffService.getUsers());}

    @GetMapping("{id}")
    public ResponseEntity<Optional<Staff>> getStaffById(@PathVariable ("id") int id) { return ResponseEntity.ok(staffService.getStaffById(id));}

    @PostMapping("/create")
    public ResponseEntity<Staff> createNewStaff(@RequestBody Staff staff) { return ResponseEntity.ok(staffService.createStaff(staff));}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStaffById(@PathVariable ("id") int id )
    {
        staffService.deleteStaffById(id);
        return ResponseEntity.accepted().build();
    }
}
