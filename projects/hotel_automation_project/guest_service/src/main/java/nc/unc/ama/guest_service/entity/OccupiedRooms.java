package nc.unc.ama.guest_service.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "occupedRooms")
@Getter
@Setter
@NoArgsConstructor
public class OccupiedRooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OccupiedRoomId;

    @Column(name = "room_Id")
    private Long roomId;

    @Column(name = "room_Id")
    private Long staffId;

    @Builder
    public OccupiedRooms(Long occupiedRoomId, Long roomId, Long staffId) {
        OccupiedRoomId = occupiedRoomId;
        this.roomId = roomId;
        this.staffId = staffId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OccupiedRooms that = (OccupiedRooms) o;
        return OccupiedRoomId.equals(that.OccupiedRoomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(OccupiedRoomId);
    }
}
