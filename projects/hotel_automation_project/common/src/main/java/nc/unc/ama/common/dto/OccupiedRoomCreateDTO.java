package nc.unc.ama.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OccupiedRoomCreateDTO {
    private Long roomId;
    private Long staffId;
    private Long guestId;
}
