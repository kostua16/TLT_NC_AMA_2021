package nc.unc.ama.booking_service.services;

import nc.unc.ama.booking_service.entities.HotelRoom;
import nc.unc.ama.booking_service.repositories.RoomsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
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
        return roomsRepo.findById(roomId)
            .map(hotelRoom -> {
                hotelRoom.setRoomBed(updatedRoom.getRoomBed());
                hotelRoom.setRoomType(updatedRoom.getRoomType());
                hotelRoom.setRoomCost(updatedRoom.getRoomCost());
                return roomsRepo.save(hotelRoom);
            })
            .orElseGet(() -> {
                updatedRoom.setRoomId(roomId);
                return roomsRepo.save(updatedRoom);
            });
    }
    @Transactional
    public void deleteRoom(Long roomId) {
        roomsRepo.deleteById(roomId);
    }

    @PostConstruct
    public void initialize(){
            List<HotelRoom> rooms = new ArrayList<>();
            Collections.addAll(rooms,
                HotelRoom
                    .builder()
                    .roomId(1L)
                    .roomBed("2 single")
                    .roomType("De luxe suite")
                    .roomCost(1500D)
                    .build(),
                HotelRoom
                    .builder()
                    .roomId(2L)
                    .roomBed("1 double")
                    .roomType("De luxe suite")
                    .roomCost(1500D)
                    .build(),
                HotelRoom
                    .builder()
                    .roomId(3L)
                    .roomBed("2 single")
                    .roomType("Comfort")
                    .roomCost(1000D)
                    .build(),
                HotelRoom
                    .builder()
                    .roomId(4L)
                    .roomBed("1 double")
                    .roomType("Comfort")
                    .roomCost(1000D)
                    .build(),
                HotelRoom
                    .builder()
                    .roomId(5L)
                    .roomBed("1 single")
                    .roomType("Comfort")
                    .roomCost(800D)
                    .build(),
                HotelRoom
                    .builder()
                    .roomId(6L)
                    .roomBed("1 double")
                    .roomType("Economy")
                    .roomCost(600D)
                    .build()

            );
            roomsRepo.saveAll(rooms);
        }
}
