package nc.unc.ama.common.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HotelRoomDTO {
    private Long roomId;
    private String roomType;
    private String roomBed;
    private Double roomCost;

    public HotelRoomDTO(Long roomId, String roomType, String roomBed, Double roomCost) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomBed = roomBed;
        this.roomCost = roomCost;
    }
}
