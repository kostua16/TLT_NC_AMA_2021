package nc.unc.ama.complaint_handling_service.controllers;

import java.util.ArrayList;
import java.util.List;
import nc.unc.ama.common.dto.ComplaintDTO;
import nc.unc.ama.common.dto.ComplaintREST;
import nc.unc.ama.common.dto.StaffDTO;
import nc.unc.ama.complaint_handling_service.entities.Complaint;
import nc.unc.ama.complaint_handling_service.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/complaint")
public class ComplaintController implements ComplaintREST {

    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping(path = "/")
    @Override
    public void createComplaint(@RequestBody ComplaintDTO complaintDTO) {
        complaintService.createComplain(Complaint
            .builder()
            .complaintText(complaintDTO.getComplaintText())
            .guestId(complaintDTO.getGuestId())
            .staffMemberId(complaintDTO.getStaffMemberId())
            .roomId(complaintDTO.getRoomId())
            .build()
        );
    }

    @GetMapping(path = "/{complaintId}")
    @Override
    public ComplaintDTO viewComplaint(@PathVariable("complaintId") Long complaintId) {
        Complaint newComplaint = complaintService.getComplaint(complaintId);
        return new ComplaintDTO(
            newComplaint.getComplaintId(),
            newComplaint.getComplaintText(),
            newComplaint.getGuestId(),
            newComplaint.getStaffMemberId(),
            newComplaint.getRoomId());
    }

    @GetMapping(path = "/")
    @Override
    public List<ComplaintDTO> getAllComplaints() {
        List<ComplaintDTO> complainDTOList = new ArrayList<>();
        for (Complaint complaint : complaintService.getAllComplaints()) {
            complainDTOList.add(new ComplaintDTO(
                complaint.getComplaintId(),
                complaint.getComplaintText(),
                complaint.getGuestId(),
                complaint.getStaffMemberId(),
                complaint.getRoomId()));
        }
        return complainDTOList;
    }

    @GetMapping(path = "/on-staff")
    @Override
    public List<ComplaintDTO> getComplaintsOnStaff(StaffDTO staffMemberDTO) {
        List<ComplaintDTO> complainDTOList = new ArrayList<>();
        for (Complaint complaint :
            complaintService.getComplaintByStaffId(staffMemberDTO.getStaffId())) {
            complainDTOList.add(new ComplaintDTO(
                complaint.getComplaintId(),
                complaint.getComplaintText(),
                complaint.getGuestId(),
                complaint.getStaffMemberId(),
                complaint.getRoomId()));
        }
        return complainDTOList;
    }
}
