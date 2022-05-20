package nc.unc.ama.common.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OccupiedRoomCreateDTO {
    private Long roomId;
    private UUID staffId;
    private UUID guestId;
}
