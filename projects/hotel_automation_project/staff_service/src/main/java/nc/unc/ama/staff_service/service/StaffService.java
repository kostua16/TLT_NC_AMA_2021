package nc.unc.ama.staff_service.service;

import nc.unc.ama.staff_service.entities.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nc.unc.ama.staff_service.repositories.StaffRepo;

@Service
public class StaffService {

    private final  StaffRepo staffRepo;
    @Autowired
    public StaffService(StaffRepo staffrepo)
    {
        this.staffRepo = staffrepo;
    }


    public Iterable<Staff> getUsers() {
        return staffRepo.findAll();
    }

    public Staff createStaff(Staff newStaff) { return  staffRepo.save(newStaff);}

    public void deleteStaffById(int staffId) {   staffRepo.deleteById(staffId);}
}
