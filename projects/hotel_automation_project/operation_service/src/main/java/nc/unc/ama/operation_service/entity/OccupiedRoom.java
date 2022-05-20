package nc.unc.ama.operation_service.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OccupiedRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long occupiedRoomId;

    private Long roomId;

    private UUID staffId;

    private UUID guestId;

    @Builder
    public OccupiedRoom(Long occupiedRoomId, Long roomId, UUID staffId, UUID guestId) {
        this.occupiedRoomId = occupiedRoomId;
        this.roomId = roomId;
        this.staffId = staffId;
        this.guestId = guestId;
    }

    public OccupiedRoom() {

    }
}
