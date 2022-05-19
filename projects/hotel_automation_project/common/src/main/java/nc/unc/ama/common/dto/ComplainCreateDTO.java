package nc.unc.ama.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComplainCreateDTO {

    private String complaintText;
    private Long guestId;
    private Long staffMemberId;
    private Long roomId;
}
