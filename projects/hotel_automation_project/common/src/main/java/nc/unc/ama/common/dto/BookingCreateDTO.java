package nc.unc.ama.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BookingCreateDTO {

    private UUID guestId;
    private Long roomId;
    private Date checkInDate;
    private Date evictionDate;

}
