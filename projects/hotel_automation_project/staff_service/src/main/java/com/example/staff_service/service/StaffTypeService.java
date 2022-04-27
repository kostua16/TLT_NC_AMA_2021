package com.example.staff_service.service;

import com.example.staff_service.entities.StaffType;
import com.example.staff_service.err.StaffTypeNotFoundException;
import com.example.staff_service.repositories.StaffTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StaffTypeService {
    private final StaffTypeRepo staffTypeRepo;

    @Autowired
    public StaffTypeService(StaffTypeRepo staffTypeRepo) {
        this.staffTypeRepo = staffTypeRepo;
    }

    @Transactional
    public void createStaffType(StaffType staffType) {
        staffTypeRepo.save(staffType);
    }

    public StaffType getStaffTypeId(Long staffTypeId) {
        return staffTypeRepo.findById(staffTypeId).orElseThrow(()->new StaffTypeNotFoundException(staffTypeId));
    }

    public List<StaffType> getaAllStaffTypes() {
        return staffTypeRepo.findAll();
    }

    @Transactional
    public void updateStaffType(StaffType updatedStaffType, Long staffTypeId) {
        updatedStaffType.setStaffTypeId(staffTypeId);
        staffTypeRepo.save(updatedStaffType);
    }

    public void deleteStaffType(Long staffTypeId) {
        staffTypeRepo.deleteById(staffTypeId);
    }
}
