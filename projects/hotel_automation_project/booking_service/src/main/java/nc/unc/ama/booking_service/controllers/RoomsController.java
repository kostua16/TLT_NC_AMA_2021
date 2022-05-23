package nc.unc.ama.booking_service.controllers;

import nc.unc.ama.booking_service.entities.HotelRoom;
import nc.unc.ama.booking_service.services.RoomsService;
import nc.unc.ama.common.dto.HotelRoomCreateDTO;
import nc.unc.ama.common.dto.HotelRoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api/rooms")
public class RoomsController {

    private final RoomsService roomsService;

    @Autowired
    public RoomsController(RoomsService roomsService)
    {
        this.roomsService = roomsService;
    }

    @PreAuthorize("hasAnyAuthority('API', 'ADMIN')")
    @PostMapping(path = "/")
    public ResponseEntity<HotelRoomDTO> createRoom(@RequestBody HotelRoomCreateDTO hotelRoomDTO){
        final HotelRoom hotelRoom = roomsService.createRoom(HotelRoom
            .builder()
            .roomType(hotelRoomDTO.getRoomType())
            .roomBed(hotelRoomDTO.getRoomBed())
            .roomCost(hotelRoomDTO.getRoomCost())
            .build()
        );
        return ResponseEntity.ok(new HotelRoomDTO(
            hotelRoom.getRoomId(),
            hotelRoom.getRoomType(),
            hotelRoom.getRoomBed(),
            hotelRoom.getRoomCost()
        ));
    }

    @PreAuthorize("hasAnyAuthority('GUEST', 'STAFF', 'API', 'ADMIN')")
    @GetMapping(path = "{id}")
    public ResponseEntity<HotelRoomDTO> viewRoom(@PathVariable("id") Long roomId)
    {
        HotelRoom newRoom = roomsService.getRoom(roomId);
        return ResponseEntity.ok(new HotelRoomDTO(
            newRoom.getRoomId(),
            newRoom.getRoomType(),
            newRoom.getRoomBed(),
            newRoom.getRoomCost()
        ));
    }

    @PreAuthorize("hasAnyAuthority('GUEST', 'STAFF', 'API', 'ADMIN')")
    @GetMapping(path = "/")
    public ResponseEntity<List<HotelRoomDTO>> getAllRooms() {
        List<HotelRoomDTO> roomDTOList = new ArrayList<>();
        for (HotelRoom room : roomsService.getAllRooms()) {
            roomDTOList.add(new HotelRoomDTO(
                room.getRoomId(),
                room.getRoomType(),
                room.getRoomBed(),
                room.getRoomCost()
            ));
        }
        return ResponseEntity.ok(roomDTOList);
    }
    @PreAuthorize("hasAnyAuthority('API', 'ADMIN')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<HotelRoomDTO> updateRoom(@PathVariable("id") Long roomId, @RequestBody HotelRoomDTO hotelRoomDTO){
        final HotelRoom hotelRoom = roomsService.updateRoom(HotelRoom
            .builder()
            .roomId(hotelRoomDTO.getRoomId())
            .roomType(hotelRoomDTO.getRoomType())
            .roomBed(hotelRoomDTO.getRoomBed())
            .roomCost(hotelRoomDTO.getRoomCost())
            .build(),
            roomId
        );
        return ResponseEntity.ok(new HotelRoomDTO(
            hotelRoom.getRoomId(),
            hotelRoom.getRoomType(),
            hotelRoom.getRoomBed(),
            hotelRoom.getRoomCost()
        ));
    }

    @PreAuthorize("hasAnyAuthority('API', 'ADMIN')")
    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") Long roomId){
        roomsService.deleteRoom(roomId);
        return ResponseEntity.ok("Room with ID = " + roomId + " was deleted");
    }
}
