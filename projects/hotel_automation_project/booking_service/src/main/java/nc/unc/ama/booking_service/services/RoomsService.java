package nc.unc.ama.booking_service.services;

import nc.unc.ama.booking_service.entities.HotelRoom;
import nc.unc.ama.booking_service.repositories.RoomsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomsService {
    private final RoomsRepo roomsRepo;

    @Autowired
    public RoomsService(RoomsRepo roomsRepo)
    {
        this.roomsRepo = roomsRepo;
    }

    @Transactional
    public HotelRoom createRoom(HotelRoom room) {
        return roomsRepo.save(room);
    }

    public HotelRoom getRoom(Long roomId) {
        return roomsRepo.findById(roomId).get();
    }

    public List<HotelRoom> getAllRooms() {
        return roomsRepo.findAll();
    }

    public List<HotelRoom> freeRooms(List<Long> occupiedRooms, Boolean costMin, Boolean costMax) {

        List<HotelRoom> listKjm = new ArrayList<>();
        if (occupiedRooms.isEmpty()){listKjm.addAll(roomsRepo.findAll());}
        if (costMin) {
            listKjm.addAll(roomsRepo.findAllByRoomIdNotInOrderByRoomCostDesc(occupiedRooms));
        } else if (costMax) {
            listKjm.addAll(roomsRepo.findAllByRoomIdNotInOrderByRoomCost(occupiedRooms));
        } else {
            listKjm.addAll(roomsRepo.findAllByRoomIdNotIn(occupiedRooms));
        }
        return listKjm;
    }

    @Transactional
    public HotelRoom updateRoom(HotelRoom updatedRoom, Long roomId) {

                updatedRoom.setRoomId(roomId);
                return roomsRepo.save(updatedRoom);
    }
    @Transactional
    public void deleteRoom(Long roomId) {
        roomsRepo.deleteById(roomId);
    }
}
