package nc.unc.ama.complaint_handling_service.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complaintId;
    private String complaintText;
    private Long guestId;
    private Long staffMemberId;
    private Long roomId;

    public Complaint(String complaintText, Long guestId, Long staffMemberId, Long roomId) {
        this.complaintText = complaintText;
        this.guestId = guestId;
        this.staffMemberId = staffMemberId;
        this.roomId = roomId;
    }

    public Complaint() {
    }

}
