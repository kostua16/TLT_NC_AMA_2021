package nc.unc.ama.booking_service.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Setter
@Getter
@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guestId;
    private String guestFName;
    private String guestLName;
    private String guestEmail;
    private String guestPhone;

    @Builder
    public Guest(Long guestId, String guestFName, String guestLName, String guestEmail, String guestPhone) {
        this.guestId = guestId;
        this.guestFName = guestFName;
        this.guestLName = guestLName;
        this.guestEmail = guestEmail;
        this.guestPhone = guestPhone;
    }

    public Guest() {

    }

}
