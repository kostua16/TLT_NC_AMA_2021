package nc.unc.ama.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class BookingCreateDTO {

    private Long guestId;
    private Long roomId;
    private Date checkInDate;
    private Date evictionDate;

}
