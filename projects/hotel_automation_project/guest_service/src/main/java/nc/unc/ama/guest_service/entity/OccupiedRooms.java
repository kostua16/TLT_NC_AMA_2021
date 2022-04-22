package nc.unc.ama.guest_service.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "occupedRooms")
@Getter
@Setter
@NoArgsConstructor
public class OccupiedRooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long occupiedRoomId;

    @Column(name = "room_Id")
    private Long roomId;

    @Column(name = "room_Id")
    private Long staffId;

    @Builder
    public OccupiedRooms(Long occupiedRoomId, Long roomId, Long staffId) {
        this.occupiedRoomId = occupiedRoomId;
        this.roomId = roomId;
        this.staffId = staffId;
    }

}
