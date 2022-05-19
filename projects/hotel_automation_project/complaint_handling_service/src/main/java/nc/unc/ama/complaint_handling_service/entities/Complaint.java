package nc.unc.ama.complaint_handling_service.entities;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaintId;
    private String complaintText;
    private Long guestId;
    private UUID staffMemberId;
    private Long roomId;
    private Long offenseId;

    @Builder
    public Complaint(Long complaintId, String complaintText, Long guestId, UUID staffMemberId, Long roomId, Long offenseId) {
        this.complaintId = complaintId;
        this.complaintText = complaintText;
        this.guestId = guestId;
        this.staffMemberId = staffMemberId;
        this.roomId = roomId;
        this.offenseId = offenseId;
    }

}
