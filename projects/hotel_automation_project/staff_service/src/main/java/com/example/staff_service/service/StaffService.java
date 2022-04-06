package com.example.staff_service.service;

import com.example.staff_service.entities.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.staff_service.repositories.StaffRepo;

import java.util.Optional;

@Service
public class StaffService {


    @Autowired
    public StaffService(StaffRepo staffrepo)
    {

        this.staffRepo = staffrepo;
    }

    private final StaffRepo staffRepo;

    public Iterable<Staff> getUsers() {
        return staffRepo.findAll();
    }

    public Staff createStaff(Staff newStaff) { return  staffRepo.save(newStaff);}

    public void deleteStaffById(int id) {   staffRepo.deleteById(id);}

    //знаю, что optional - это плохо, но не знаю, как его заменить
    public Optional<Staff> getStaffById(int id) {
        return staffRepo.findById(id);
    }
}
