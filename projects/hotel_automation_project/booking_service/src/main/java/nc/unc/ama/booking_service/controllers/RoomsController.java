package nc.unc.ama.booking_service.controllers;

import nc.unc.ama.booking_service.entities.HotelRoom;
import nc.unc.ama.booking_service.services.RoomsService;
import nc.unc.ama.complaint_handling_service.dto.HotelRoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/rooms")
public class RoomsController {

    private final RoomsService roomsService;

    @Autowired
    public RoomsController(RoomsService roomsService)
    {
        this.roomsService = roomsService;
    }

    @PostMapping(path = "/")
    public void createRoom(@RequestBody HotelRoomDTO hotelRoomDTO){
        roomsService.createRoom(HotelRoom
            .builder()
            .roomId(hotelRoomDTO.getRoomId())
            .roomType(hotelRoomDTO.getRoomType())
            .roomBed(hotelRoomDTO.getRoomBed())
            .roomCost(hotelRoomDTO.getRoomCost())
            .build()
        );
    }

    @GetMapping(path = "{roomId}")
    public HotelRoomDTO viewRoom(@PathVariable("roomId") Long roomId)
    {
        HotelRoom newRoom = roomsService.getRoom(roomId);
        return new HotelRoomDTO(
            newRoom.getRoomId(),
            newRoom.getRoomType(),
            newRoom.getRoomBed(),
            newRoom.getRoomCost()
        );
    }
    @GetMapping(path = "/")
    public List<HotelRoomDTO> getAllRooms() {
        List<HotelRoomDTO> roomDTOList = new ArrayList<>();
        for (HotelRoom room : roomsService.getAllRooms()) {
            roomDTOList.add(new HotelRoomDTO(
                room.getRoomId(),
                room.getRoomType(),
                room.getRoomBed(),
                room.getRoomCost()
            ));
        }
        return roomDTOList;
    }
    @PutMapping(path = "/{roomId)")
    public void updateRoom(@PathVariable("roomId") Long roomId, @RequestBody HotelRoomDTO hotelRoomDTO){
        roomsService.updateRoom(HotelRoom
            .builder()
            .roomId(hotelRoomDTO.getRoomId())
            .roomType(hotelRoomDTO.getRoomType())
            .roomBed(hotelRoomDTO.getRoomBed())
            .roomCost(hotelRoomDTO.getRoomCost())
            .build(),
            roomId
        );
    }

    @DeleteMapping(path="/{roomId)")
    public void deleteRoom(@PathVariable("roomId") Long roomId){
        roomsService.deleteRoom(roomId);
    }
}
