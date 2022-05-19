package nc.unc.ama.common.dto;

import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "complaints", path = "/complaint")
@ConditionalOnProperty(prefix = "app.clients", name = "complaintsApi")
public interface ComplaintREST {

    @PostMapping("/createComplaint")
    ResponseEntity<ComplaintDTO> createComplaint(@RequestBody ComplainCreateDTO complaint);

    @GetMapping(path = "/{complaintId}")
    ResponseEntity<ComplaintDTO> viewComplaint(@PathVariable("complaintId") Long complaintId);

    @GetMapping(path = "/")
    ResponseEntity<List<ComplaintDTO>> getAllComplaints();

    @GetMapping(path = "/on-staff")
    ResponseEntity<List<ComplaintDTO>> getComplaintsOnStaff(StaffDTO staffMemberDTO);
}
