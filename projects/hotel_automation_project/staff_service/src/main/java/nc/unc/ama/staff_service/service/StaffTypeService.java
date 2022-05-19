package nc.unc.ama.staff_service.service;

import nc.unc.ama.staff_service.entities.StaffType;
import nc.unc.ama.staff_service.err.StaffTypeNotFoundException;
import nc.unc.ama.staff_service.repositories.StaffTypeRepo;
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
    public StaffType createStaffType(StaffType staffType) {
        return staffTypeRepo.save(staffType);
    }

    public StaffType getStaffTypeId(Long staffTypeId) {
        return staffTypeRepo.findById(staffTypeId).orElseThrow(()->new StaffTypeNotFoundException(staffTypeId));
    }

    public List<StaffType> getaAllStaffTypes() {
        return staffTypeRepo.findAll();
    }

    @Transactional
    public StaffType updateStaffType(StaffType updatedStaffType, Long staffTypeId) {
        updatedStaffType.setStaffTypeId(staffTypeId);
        return staffTypeRepo.save(updatedStaffType);
    }

    public void deleteStaffType(Long staffTypeId) {
        staffTypeRepo.deleteById(staffTypeId);
    }
}
