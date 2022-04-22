package nc.unc.ama.complaint_handling_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class BookingDTO {
    private Long bookingId;
    private Long guestId;
    private Long roomId;
    private Date checkInDate;
    private Date evictionDate;
    private Boolean bookingCost;

    @Builder
    public BookingDTO(Long bookingId, Long guestId, Long roomId, Date checkInDate, Date evictionDate, Boolean bookingCost) {
        this.bookingId = bookingId;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.evictionDate = evictionDate;
        this.bookingCost = bookingCost;
    }
}
