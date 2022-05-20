package nc.unc.ama.common.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class BookingDTO extends BookingCreateDTO{
    private final Long bookingId;

    @Builder
    public BookingDTO(Long bookingId, UUID guestId, Long roomId, Date checkInDate, Date evictionDate) {
        super(guestId, roomId, checkInDate, evictionDate);
        this.bookingId = bookingId;
    }

}
