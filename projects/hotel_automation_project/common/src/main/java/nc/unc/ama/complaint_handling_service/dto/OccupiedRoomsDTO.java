package nc.unc.ama.complaint_handling_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OccupiedRoomsDTO {

    private Long occupiedRoomId;
    private Long roomId;
    private Long staffId;

    public OccupiedRoomsDTO() {
    }


    public OccupiedRoomsDTO(Long occupiedRoomId, Long roomId, Long staffId) {
        this.occupiedRoomId = occupiedRoomId;
        this.roomId = roomId;
        this.staffId = staffId;
    }
}
