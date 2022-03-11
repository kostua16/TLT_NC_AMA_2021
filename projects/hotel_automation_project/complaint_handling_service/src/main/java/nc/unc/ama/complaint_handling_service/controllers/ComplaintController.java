package nc.unc.ama.complaint_handling_service.controllers;

import nc.unc.ama.complaint_handling_service.dto.StaffMemberDTO;
import nc.unc.ama.complaint_handling_service.entities.Complaint;
import nc.unc.ama.complaint_handling_service.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/Complaint")
public class ComplaintController {

    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping(path = "/createComplaint")
    public void createComplaint(@RequestBody Complaint complaint){
        complaintService.createComplain(complaint);
    }

    @GetMapping(path = "/viewComplaint/{complaintId}")
    public Complaint viewComplaint(@PathVariable("complaintId") Long complaintId){
        return complaintService.getComplaint(complaintId);
    }
    @GetMapping(path = "/viewAllComplaints")
    public List<Complaint> getAllComplaints(){
        return  complaintService.getAllComplaints();
    }
    @GetMapping
    public Optional<List<Complaint>> getComplaintsOnStaff(StaffMemberDTO staffMemberDTO){
        return complaintService.getComplaintByStaffId(staffMemberDTO.getStaffMemberId());
    }
}
