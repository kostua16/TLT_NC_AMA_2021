package nc.unc.ama.staff_service.service;

import nc.unc.ama.staff_service.entities.Staff;
import nc.unc.ama.staff_service.repositories.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StaffService {

    private final  StaffRepo staffRepo;
    @Autowired
    public StaffService(StaffRepo staffrepo)
    {
        this.staffRepo = staffrepo;
    }

    public List<Staff> getAllStaff() {
        return staffRepo.findAll();
    }

    @Transactional
    public Staff createStaff(Staff newStaff) { return  staffRepo.save(newStaff);}
    @Transactional
    public void deleteStaffById(Long staffId) {   staffRepo.deleteById(staffId);}

    public Staff getStaff(Long staffId) {
        return staffRepo.findById(staffId).get();
    }
    @Transactional
    public void updateStaff(Staff updatedStaff, Long staffId) {

                updatedStaff.setStaffId(staffId);
                staffRepo.save(updatedStaff);
    }
}
