package nc.unc.ama.complaint_handling_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StaffMemberDTO {

    private Long staffMemberId;
    private String firstname;
    private String lastname;
    private String typeOfStaff;
    private Integer staffRating;

    public StaffMemberDTO(String firstname, String lastname, String typeOfStaff, Integer staffRating) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.typeOfStaff = typeOfStaff;
        this.staffRating = staffRating;
    }

    public StaffMemberDTO() {
    }

}
