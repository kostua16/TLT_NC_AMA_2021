package nc.unc.ama.complaint_handling_service.controllers;

import nc.unc.ama.complaint_handling_service.dto.StaffMemberDTO;
import nc.unc.ama.complaint_handling_service.dto.StaffRatingREST;
import nc.unc.ama.complaint_handling_service.services.StaffRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/staff-rating")
public class RatingControllerImpl implements StaffRatingREST {
    private final StaffRatingService staffRatSer;

    @Autowired
    public RatingControllerImpl(StaffRatingService staffRatSer) {
        this.staffRatSer= staffRatSer;
    }

    @Override
    @PostMapping("/changeRating")
    public void changeRating(@RequestBody Integer staffRating){
        staffRatSer.changeStaffRating(staffRating);
    }
    @Override
    @GetMapping("/viewRatingOfStaff")
    public StaffMemberDTO viewRatingOfStaff(@PathVariable("staffMemberId") Long staffMemberId){
        return staffRatSer.getStaffRating(staffMemberId);
    }

}
