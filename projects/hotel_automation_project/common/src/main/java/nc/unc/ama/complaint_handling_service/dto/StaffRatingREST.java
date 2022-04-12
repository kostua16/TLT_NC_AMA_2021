package nc.unc.ama.complaint_handling_service.dto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "staff_service", path = "/staff-rating")
public interface StaffRatingREST {

    @PostMapping("/change-rating")
    void changeRating(@RequestBody Integer staffRating);


    @GetMapping("/view-rating-of-staff")
    StaffMemberDTO viewRatingOfStaff(@PathVariable("staffMemberId") Long staffMemberId);
}
