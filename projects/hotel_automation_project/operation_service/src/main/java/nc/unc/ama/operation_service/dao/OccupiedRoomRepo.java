package nc.unc.ama.operation_service.dao;

import java.util.UUID;
import nc.unc.ama.operation_service.entity.OccupiedRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccupiedRoomRepo extends JpaRepository<OccupiedRoom,Long> {

    Long findOccupiedRoomByGuestId(UUID guestId);
}
