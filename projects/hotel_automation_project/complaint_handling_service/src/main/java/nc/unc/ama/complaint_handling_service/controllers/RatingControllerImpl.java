package nc.unc.ama.complaint_handling_service.controllers;

import nc.unc.ama.common.dto.StaffDTO;
import nc.unc.ama.common.dto.StaffREST;
import nc.unc.ama.common.dto.UserDTO;
import nc.unc.ama.common.dto.UserInfoDTO;
import nc.unc.ama.common.dto.UserRoles;
import nc.unc.ama.common.dto.UsersREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController("/staff-rating")
public class RatingControllerImpl {
    private final UsersREST usersREST;

    @Autowired
    public RatingControllerImpl(final UsersREST usersREST) {
        this.usersREST = usersREST;
    }


    public ResponseEntity<HttpStatus> rateUp(final UUID id, int value) {
        return ResponseEntity.status(this.usersREST.rateSet(id, value).getStatusCode()).build();
    }


    public ResponseEntity<List<UserDTO>> getAllStaff() {
        final ResponseEntity<List<UserDTO>> result;
        final ResponseEntity<List<UserInfoDTO>> response = usersREST.listStaff();
        final ResponseEntity.BodyBuilder builder = ResponseEntity.status(response.getStatusCode());
        final List<UserInfoDTO> body = response.getBody();
        if (body != null) {
            result = builder.body(
                body.stream().map(UserInfoDTO::toSummary).collect(Collectors.toList())
            );
        } else {
            result = builder.build();
        }
        return result;
    }


    public ResponseEntity<UserDTO> getStaff(UUID staffId) {
        final ResponseEntity<UserDTO> result;
        final ResponseEntity<UserInfoDTO> user = this.usersREST.getUser(staffId);
        final UserInfoDTO body = user.getBody();
        if (body != null && UserRoles.STAFF == body.getRole()) {
             result = ResponseEntity.status(user.getStatusCode()).body(body.toSummary());
        } else {
            result = ResponseEntity.status(user.getStatusCode()).build();
        }
        return result;
    }
}
