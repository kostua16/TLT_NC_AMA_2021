package nc.unc.ama.complaint_handling_service.dto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ComplaintREST {
    @PostMapping("/createComplaint")
    void createComplaint(@RequestBody ComplaintDTO complaint);

    @GetMapping("/viewComplaint")
    ComplaintDTO viewComplaint();
}
