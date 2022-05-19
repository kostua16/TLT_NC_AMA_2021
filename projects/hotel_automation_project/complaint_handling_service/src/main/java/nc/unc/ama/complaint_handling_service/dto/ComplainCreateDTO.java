package nc.unc.ama.complaint_handling_service.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComplainCreateDTO {

    private String complaintText;
    private UUID guestId;
    private UUID staffMemberId;
    private Long roomId;
    private Long offenseId;
}
