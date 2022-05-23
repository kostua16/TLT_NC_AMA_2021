package nc.unc.ama.complaint_handling_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComplainCreateDTO {

    private String complaintText;
    private Long guestId;
    private Long staffMemberId;
    private Long roomId;
    private Long offenseId;
}
