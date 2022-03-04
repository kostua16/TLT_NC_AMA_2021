package nc.unc.ama.complaint_handling_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ComplaintDTO {

    private Long complaintId;
    private String complaintText;
    private Long guestId;
    private Long staffMemberId;
    private Long roomId;

    public ComplaintDTO(String complaintText, Long guestId, Long staffMemberId, Long roomId) {
        this.complaintText = complaintText;
        this.guestId = guestId;
        this.staffMemberId = staffMemberId;
        this.roomId = roomId;
    }

    public ComplaintDTO() {
    }

}
