package nc.unc.ama.common.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ComplaintDTO extends ComplainCreateDTO{

    private Long complaintId;

    public ComplaintDTO(Long complaintId, String complaintText, Long guestId, Long staffMemberId, Long roomId) {
        super(complaintText, guestId, staffMemberId, roomId);
        this.complaintId = complaintId;
    }
}
