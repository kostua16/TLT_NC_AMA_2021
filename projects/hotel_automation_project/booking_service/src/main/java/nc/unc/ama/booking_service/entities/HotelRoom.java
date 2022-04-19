package nc.unc.ama.booking_service.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Setter
@Getter
@Entity
public class HotelRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    private String roomType;
    private String roomBed;
    private Double roomCost;
    @Builder
    public HotelRoom(Long roomId, String roomType, String roomBed, Double roomCost) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomBed = roomBed;
        this.roomCost = roomCost;
    }

    public HotelRoom() {

    }
}
