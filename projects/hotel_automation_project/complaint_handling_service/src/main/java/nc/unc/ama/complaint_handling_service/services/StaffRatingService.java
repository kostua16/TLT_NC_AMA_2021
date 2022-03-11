package nc.unc.ama.complaint_handling_service.services;

import nc.unc.ama.complaint_handling_service.dto.StaffMemberDTO;
import org.springframework.stereotype.Service;

@Service
public class StaffRatingService {


    public void changeStaffRating(Integer staffRating) {

    }

    public StaffMemberDTO getStaffRating(Long staffMemberId) {
        return new StaffMemberDTO();
    }
}
