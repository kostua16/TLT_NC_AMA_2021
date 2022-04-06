package nc.unc.ama.complaint_handling_service.services;


import nc.unc.ama.complaint_handling_service.entities.Complaint;
import nc.unc.ama.complaint_handling_service.repositories.ComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class ComplaintService {
    private final ComplaintRepo complaintRepo;

    @Autowired
    public ComplaintService(ComplaintRepo complaintRepo) {

        this.complaintRepo = complaintRepo;
    }


    public Complaint getComplaint(Long complaintId) {

        return complaintRepo.findById(complaintId).get();
    }

    @Transactional
    public void createComplain(Complaint complaint){
        complaintRepo.save(complaint);
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepo.findAll();
    }

    public List<Complaint> getComplaintByStaffId(Long staffMemberId) {
        return complaintRepo.findComplaintByStaffMemberId(staffMemberId);
    }

}
