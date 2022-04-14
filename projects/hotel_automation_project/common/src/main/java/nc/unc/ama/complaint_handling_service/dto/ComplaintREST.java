package nc.unc.ama.complaint_handling_service.dto;

import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "complaints", path = "/complaint")
@ConditionalOnProperty(prefix = "app.clients", name = "complaintsApi")
public interface ComplaintREST {

    @PostMapping("/createComplaint")
    void createComplaint(@RequestBody ComplaintDTO complaint);

    @GetMapping(path = "/{complaintId}")
    ComplaintDTO viewComplaint(@PathVariable("complaintId") Long complaintId);

    @GetMapping(path = "/")
    List<ComplaintDTO> getAllComplaints();

    @GetMapping(path = "/on-staff")
    List<ComplaintDTO> getComplaintsOnStaff(StaffMemberDTO staffMemberDTO);
}
