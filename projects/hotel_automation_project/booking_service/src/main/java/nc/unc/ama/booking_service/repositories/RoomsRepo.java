package nc.unc.ama.booking_service.repositories;

import nc.unc.ama.booking_service.entities.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsRepo extends JpaRepository<HotelRoom, Long>{

    List<HotelRoom> findAllByRoomIdNotIn(List<Long> rooms);
    List<HotelRoom> findAllByRoomIdNotInOrderByRoomCost(List<Long> rooms);
    List<HotelRoom> findAllByRoomIdNotInOrderByRoomCostDesc(List<Long> rooms);

}
