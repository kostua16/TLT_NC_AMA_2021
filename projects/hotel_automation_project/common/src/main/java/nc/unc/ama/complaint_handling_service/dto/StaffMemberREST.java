package nc.unc.ama.complaint_handling_service.dto;



import org.springframework.web.bind.annotation.*;



public interface StaffMemberREST {

    @PutMapping("/changeRating")
    void changeRating(@RequestBody Integer staffRating);


    @GetMapping("/viewRatingOfStaff")
    StaffMemberDTO viewRatingOfStaff(@PathVariable("staffMemberId") Long staffMemberId);
}
