package nc.unc.ama.complaint_handling_service.services;


import nc.unc.ama.complaint_handling_service.entities.Complaint;
import nc.unc.ama.complaint_handling_service.repositories.ComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public void createComplain(String complaintText, Long guestId, Long staffMemberId, Long roomId){
        complaintRepo.save(new Complaint(
        complaintText,
        guestId,
        staffMemberId,
        roomId)
        );
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepo.findAll();
    }

    public List<Complaint> getComplaintByStaffId(Long staffMemberId) {
        return complaintRepo.findComplaintByStaffMemberId(staffMemberId);
    }
}
