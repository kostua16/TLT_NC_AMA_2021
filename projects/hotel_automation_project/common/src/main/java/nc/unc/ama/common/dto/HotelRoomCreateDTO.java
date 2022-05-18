package nc.unc.ama.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HotelRoomCreateDTO {
    private String roomType;
    private String roomBed;
    private Double roomCost;
}
