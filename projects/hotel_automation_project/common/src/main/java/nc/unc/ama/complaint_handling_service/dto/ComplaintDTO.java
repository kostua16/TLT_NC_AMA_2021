package nc.unc.ama.complaint_handling_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintDTO {

    private Long complaintId;
    private String complaintText;
    private Long guestId;
    private Long staffMemberId;
    private Long roomId;


    public ComplaintDTO(Long complaintId, String complaintText, Long guestId, Long staffMemberId, Long roomId) {
        this.complaintId = complaintId;
        this.complaintText = complaintText;
        this.guestId = guestId;
        this.staffMemberId = staffMemberId;
        this.roomId = roomId;
    }

}
