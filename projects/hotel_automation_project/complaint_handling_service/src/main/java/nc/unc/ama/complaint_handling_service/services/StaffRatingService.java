package nc.unc.ama.complaint_handling_service.services;

import lombok.extern.slf4j.Slf4j;
import nc.unc.ama.common.dto.StaffDTO;
/*import nc.unc.ama.complaint_handling_service.dto.StaffMemberREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;*/
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StaffRatingService {
    /*private final StaffMemberREST staffMemberREST;

    @Autowired
    public StaffRatingService(@Qualifier("nc.unc.ama.complaint_handling_service.dto.StaffMemberREST") final StaffMemberREST staffMemberREST) {
        this.staffMemberREST = staffMemberREST;
    }*/

    public void changeStaffRating(Integer staffRating) {

    }

    public StaffDTO getStaffRating(Long staffMemberId) {
        return new StaffDTO();
    }
}
