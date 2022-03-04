package nc.unc.ama.complaint_handling_service.dto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface StaffMemberREST {

    @PostMapping("/changeRating")
    void changeRating(@RequestBody Integer staffRating);


    @GetMapping("viewRatingOfStaff")
    StaffMemberDTO viewRatingOfStaff();
}
