package nc.unc.ama.complaint_handling_service.controllers;

import nc.unc.ama.common.dto.StaffDTO;
import nc.unc.ama.common.dto.StaffREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/staff-rating")
public class RatingControllerImpl {
    private final StaffREST staffREST;

    @Autowired
    public RatingControllerImpl(StaffREST staffREST) {
        this.staffREST = staffREST;
    }


    public ResponseEntity<HttpStatus> changeRating(Long staffId, Integer points, Boolean plusOrSub) {
        staffREST.changeRating(staffId,points,plusOrSub);
        return ResponseEntity.accepted().build();
    }


    public ResponseEntity<List<StaffDTO>> getAllStaff() {
        return staffREST.getAllStaff();
    }


    public ResponseEntity<StaffDTO> getStaff(Long staffId) {
        return staffREST.getStaff(staffId);
    }
}
