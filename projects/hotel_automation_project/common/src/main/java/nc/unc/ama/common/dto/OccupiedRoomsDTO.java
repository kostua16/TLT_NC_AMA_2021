package nc.unc.ama.common.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class OccupiedRoomsDTO extends OccupiedRoomCreateDTO{

    private Long occupiedRoomId;
    @Builder
    public OccupiedRoomsDTO(Long occupiedRoomId, Long roomId, UUID staffId, UUID guestId) {
        super(roomId, staffId, guestId);
        this.occupiedRoomId = occupiedRoomId;
    }
}
