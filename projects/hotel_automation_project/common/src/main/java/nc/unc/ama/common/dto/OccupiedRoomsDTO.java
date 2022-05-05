package nc.unc.ama.common.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OccupiedRoomsDTO {

    private Long occupiedRoomId;
    private Long staffMemberId;
    private Long bookingId;

    public OccupiedRoomsDTO() {
    }

    public OccupiedRoomsDTO(Long staffMemberId, Long bookingId) {
        this.staffMemberId = staffMemberId;
        this.bookingId = bookingId;
    }

}
