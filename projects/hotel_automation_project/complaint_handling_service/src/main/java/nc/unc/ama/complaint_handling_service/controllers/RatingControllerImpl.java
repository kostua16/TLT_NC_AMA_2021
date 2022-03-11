package nc.unc.ama.complaint_handling_service.controllers;

import nc.unc.ama.complaint_handling_service.dto.StaffMemberDTO;
import nc.unc.ama.complaint_handling_service.dto.StaffMemberREST;
import nc.unc.ama.complaint_handling_service.services.StaffRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RatingControllerImpl implements StaffMemberREST {
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
