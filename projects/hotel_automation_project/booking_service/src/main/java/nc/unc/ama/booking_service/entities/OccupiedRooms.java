package nc.unc.ama.booking_service.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class OccupiedRooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long occupiedRoomsId;
    private Long guestId;

    public OccupiedRooms(Long occupiedRoomsId, Long guestId)
    {
        this.guestId = guestId;
        this.occupiedRoomsId = occupiedRoomsId;
    }

    public OccupiedRooms()
    {

    }
}

