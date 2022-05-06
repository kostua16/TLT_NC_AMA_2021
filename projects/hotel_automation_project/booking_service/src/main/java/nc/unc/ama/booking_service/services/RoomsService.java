package nc.unc.ama.booking_service.services;

import nc.unc.ama.booking_service.entities.HotelRoom;
import nc.unc.ama.booking_service.repositories.RoomsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public void createRoom(HotelRoom room) {
        roomsRepo.save(room);
    }

    public HotelRoom getRoom(Long roomId) {
        return roomsRepo.findById(roomId).get();
    }

    public List<HotelRoom> getAllRooms() {
        return roomsRepo.findAll();
    }

    @Transactional
    public void updateRoom(HotelRoom updatedRoom, Long roomId) {
            roomsRepo.findById(roomId)
            .map(room -> {
                room.setRoomBed(updatedRoom.getRoomBed());
                room.setRoomType(updatedRoom.getRoomType());
                room.setRoomCost(updatedRoom.getRoomCost());
                return roomsRepo.save(room);
            })
            .orElseGet(() -> {
                updatedRoom.setRoomId(roomId);
                return roomsRepo.save(updatedRoom);
            });
    }

    public void deleteRoom(Long roomId) {
        roomsRepo.deleteById(roomId);
    }
}
