package nc.unc.ama.operation_service.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class OccupiedRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long occupiedRoomId;
    private Long roomId;
    private Long staffId;
    private Long guestId;

    @Builder
    public OccupiedRoom(Long occupiedRoomId, Long roomId, Long staffId, Long guestId) {
        this.occupiedRoomId = occupiedRoomId;
        this.roomId = roomId;
        this.staffId = staffId;
        this.guestId = guestId;
    }

    public OccupiedRoom() {

    }
}
