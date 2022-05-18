package nc.unc.ama.complaint_handling_service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ComplaintDTO extends ComplainCreateDTO{

    private Long complaintId;

    public ComplaintDTO(Long complaintId, String complaintText, Long guestId, Long staffMemberId, Long roomId, Long offenseId) {
        super(complaintText, guestId, staffMemberId, roomId, offenseId);
        this.complaintId = complaintId;
    }
}
