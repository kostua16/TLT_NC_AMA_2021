package nc.unc.ama.common.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GuestDTO {

    private Long guestId;
    private String firstname;
    private  String lastname;
    private String guestEmail;

    public GuestDTO() {
    }

    public GuestDTO(String firstname, String lastname, String guestEmail) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.guestEmail = guestEmail;
    }

}
