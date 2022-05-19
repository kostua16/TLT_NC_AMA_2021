package nc.unc.ama.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Deprecated
public class GuestCreationDTO {

    private String guestFName;

    private String guestLName;

    private String guestEmail;

    private String guestPhone;
}
