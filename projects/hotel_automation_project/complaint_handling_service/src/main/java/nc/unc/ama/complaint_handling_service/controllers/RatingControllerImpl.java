package nc.unc.ama.complaint_handling_service.controllers;

import nc.unc.ama.complaint_handling_service.dto.StaffMemberDTO;
import nc.unc.ama.complaint_handling_service.dto.StaffMemberREST;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RatingControllerImpl implements StaffMemberREST {

    @Override
    @PostMapping("/changeRating")
    public void changeRating(@RequestBody Integer staffRating){

    }
    @Override
    @GetMapping("viewRatingOfStaff")
    public StaffMemberDTO viewRatingOfStaff(){
        return new StaffMemberDTO();
    }

}
