package nc.unc.ama.complaint_handling_service.dto;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "staff_service", path = "/staff-rating")
@ConditionalOnProperty(prefix = "app.clients", name = "staffRatingApi")
public interface StaffRatingREST {

    @PostMapping("/change-rating")
    void changeRating(@RequestBody Integer staffRating);

    @GetMapping("/view-rating-of-staff")
    StaffDTO viewRatingOfStaff(@PathVariable("staffMemberId") Long staffMemberId);


}
