package nc.unc.ama.complaint_handling_service.controllers;

import java.util.ArrayList;
import java.util.List;
import nc.unc.ama.complaint_handling_service.dto.ComplainCreateDTO;
import nc.unc.ama.complaint_handling_service.dto.ComplaintDTO;
import nc.unc.ama.complaint_handling_service.entities.Complaint;
import nc.unc.ama.complaint_handling_service.services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping(path = "/")
    @PreAuthorize("hasAnyAuthority('GUEST', 'API', 'ADMIN')")
    public ResponseEntity<ComplaintDTO> createComplaint(@RequestBody ComplainCreateDTO complaintDTO) {
        final Complaint complaint = complaintService.createComplain(Complaint
            .builder()
            .complaintText(complaintDTO.getComplaintText())
            .guestId(complaintDTO.getGuestId())
            .staffMemberId(complaintDTO.getStaffMemberId())
            .roomId(complaintDTO.getRoomId())
            .offenseId(complaintDTO.getOffenseId())
            .build()
        );
        return ResponseEntity.ok(new ComplaintDTO(
            complaint.getComplaintId(),
            complaint.getComplaintText(),
            complaint.getGuestId(),
            complaint.getStaffMemberId(),
            complaint.getRoomId(),
            complaint.getOffenseId()));
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority('GUEST', 'STAFF', 'API', 'ADMIN')")
    public ResponseEntity<ComplaintDTO> viewComplaint(@PathVariable("id") Long complaintId) {
        Complaint complaint = complaintService.getComplaint(complaintId);
        return ResponseEntity.ok(new ComplaintDTO(
            complaint.getComplaintId(),
            complaint.getComplaintText(),
            complaint.getGuestId(),
            complaint.getStaffMemberId(),
            complaint.getRoomId(),
            complaint.getOffenseId()));
    }

    @GetMapping(path = "/")
    @PreAuthorize("hasAnyAuthority('STAFF', 'API', 'ADMIN')")
    public ResponseEntity<List<ComplaintDTO>> getAllComplaints() {
        List<ComplaintDTO> complainDTOList = new ArrayList<>();
        for (Complaint complaint : complaintService.getAllComplaints()) {
            complainDTOList.add(new ComplaintDTO(
                complaint.getComplaintId(),
                complaint.getComplaintText(),
                complaint.getGuestId(),
                complaint.getStaffMemberId(),
                complaint.getRoomId(),
                complaint.getOffenseId()));
        }
        return ResponseEntity.ok(complainDTOList);
    }

    @GetMapping(path = "/on-staff/{id}")
    @PreAuthorize("hasAnyAuthority('STAFF', 'API', 'ADMIN')")
    public ResponseEntity<List<ComplaintDTO>> getComplaintsOnStaff(@PathVariable("id") Long user) {
        List<ComplaintDTO> complainDTOList = new ArrayList<>();
        for (Complaint complaint :
            complaintService.getComplaintByStaffId(user)) {
            complainDTOList.add(new ComplaintDTO(
                complaint.getComplaintId(),
                complaint.getComplaintText(),
                complaint.getGuestId(),
                complaint.getStaffMemberId(),
                complaint.getRoomId(),
                complaint.getOffenseId()));
        }
        return ResponseEntity.ok(complainDTOList);
    }
}
