package nc.unc.ama.booking_service.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Setter
@Getter
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private Long guestId;
    private Long roomId;
    private Date checkInDate;
    private Date evictionDate;
    private Boolean bookingCost;

    @Builder
    public Booking(Long bookingId, Long guestId, Long roomId, Date checkInDate, Date evictionDate, Boolean bookingCost) {
        this.bookingId = bookingId;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.evictionDate = evictionDate;
        this.bookingCost = bookingCost;
    }

    public Booking() {
    }
}
