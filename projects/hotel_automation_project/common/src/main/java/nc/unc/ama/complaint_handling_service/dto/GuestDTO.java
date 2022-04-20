package nc.unc.ama.complaint_handling_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GuestDTO {

    private Long guestId;
    private String guestFName;
    private String guestLName;
    private String guestEmail;
    private String guestPhone;

    public GuestDTO(Long guestId, String guestFName, String guestLName, String guestEmail, String guestPhone) {
        this.guestId = guestId;
        this.guestFName = guestFName;
        this.guestLName = guestLName;
        this.guestEmail = guestEmail;
        this.guestPhone = guestPhone;
    }

    public GuestDTO() {
    }
}
