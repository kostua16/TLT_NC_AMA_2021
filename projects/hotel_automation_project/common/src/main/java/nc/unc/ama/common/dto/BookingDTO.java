package nc.unc.ama.common.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class BookingDTO extends BookingCreateDTO{
    private final Long bookingId;

    @Builder
    public BookingDTO(Long bookingId, Long guestId, Long roomId, Date checkInDate, Date evictionDate, Boolean bookingCost) {
        super(guestId, roomId, checkInDate, evictionDate, bookingCost);
        this.bookingId = bookingId;
    }

}
