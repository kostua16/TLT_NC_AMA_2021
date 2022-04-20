package nc.unc.ama.guest_service.dao;

import nc.unc.ama.guest_service.entity.OccupiedRooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OcRoomsRepo extends JpaRepository<OccupiedRooms,Long> {

}
