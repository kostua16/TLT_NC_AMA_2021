package com.example.staff_service.service;

import com.example.staff_service.entities.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.staff_service.repositories.StaffRepo;

@Service
public class StaffService {


    @Autowired
    private StaffRepo staffRepo;

    public Iterable<Staff> getUsers() {
        return staffRepo.findAll();
    }

    public Staff createStaff(Staff newStaff) { return  staffRepo.save(newStaff);}

    public void deleteStaffById(int id) {   staffRepo.deleteById(id);}
}
