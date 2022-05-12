package nc.unc.ama.common.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class HotelRoomDTO extends HotelRoomCreateDTO{
    private Long roomId;

    public HotelRoomDTO(Long roomId, String roomType, String roomBed, Double roomCost) {
        super(roomType, roomBed, roomCost);
        this.roomId = roomId;
    }
}
