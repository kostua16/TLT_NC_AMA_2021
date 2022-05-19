package nc.unc.ama.common.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class GuestDTO extends GuestCreationDTO {

    private final Long guestId;

    public GuestDTO(String guestFName, String guestLName, String guestEmail, String guestPhone,
                    Long guestId) {
        super(guestFName, guestLName, guestEmail, guestPhone);
        this.guestId = guestId;
    }
}
