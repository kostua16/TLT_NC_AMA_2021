package nc.unc.ama.operation_service.controller;

import nc.unc.ama.common.dto.OccupiedRoomCreateDTO;
import nc.unc.ama.common.dto.OccupiedRoomsDTO;
import nc.unc.ama.operation_service.entity.OccupiedRoom;
import nc.unc.ama.operation_service.service.OccupiedRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/occupied-rooms")
public class OccupiedRoomsController {

    private final OccupiedRoomService occupRoomSer;

    @Autowired
    public OccupiedRoomsController(OccupiedRoomService occupRoomSer) {
        this.occupRoomSer = occupRoomSer;
    }

    @GetMapping("/")
    public ResponseEntity<List<OccupiedRoomsDTO>> showAllOccupiedRooms() {
        List<OccupiedRoomsDTO> dtoList = new ArrayList<>();
        for (OccupiedRoom occupiedRoom : occupRoomSer.getAllOccupiedRooms()) {
            dtoList.add(new OccupiedRoomsDTO(
                occupiedRoom.getOccupiedRoomId(),
                occupiedRoom.getRoomId(),
                occupiedRoom.getStaffId(),
                occupiedRoom.getGuestId()));
        }
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OccupiedRoomsDTO> getOccupiedRoom(@PathVariable("id") Long idOccupiedRoom) {
        OccupiedRoom occupiedRoom = occupRoomSer.getOccupiedRoom(idOccupiedRoom);
        return ResponseEntity.ok(new OccupiedRoomsDTO(
            occupiedRoom.getOccupiedRoomId(),
            occupiedRoom.getRoomId(),
            occupiedRoom.getStaffId(),
            occupiedRoom.getGuestId()));
    }

    @PostMapping("/")
    public ResponseEntity<OccupiedRoomsDTO> addNewOccupiedRoom(@RequestBody OccupiedRoomCreateDTO createDTO) {
        final OccupiedRoom occupiedRoom = occupRoomSer.addOccupiedRoom(OccupiedRoom
            .builder()
            .roomId(createDTO.getRoomId())
            .staffId(createDTO.getStaffId())
            .guestId(createDTO.getGuestId())
            .build());
        return ResponseEntity.ok(new OccupiedRoomsDTO(
            occupiedRoom.getOccupiedRoomId(),
            occupiedRoom.getRoomId(),
            occupiedRoom.getStaffId(),
            occupiedRoom.getGuestId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OccupiedRoomsDTO> updateOccupiedRoom(@PathVariable("id") Long idOccupiedRoom,@RequestBody OccupiedRoomsDTO occupiedRoomsDTO) {
        final OccupiedRoom occupiedRoom = occupRoomSer.updateOccupiedRoom(OccupiedRoom
                .builder()
                .occupiedRoomId(occupiedRoomsDTO.getOccupiedRoomId())
                .roomId(occupiedRoomsDTO.getRoomId())
                .staffId(occupiedRoomsDTO.getStaffId())
                .guestId(occupiedRoomsDTO.getGuestId())
                .build(),
            idOccupiedRoom
        );
        return ResponseEntity.ok(new OccupiedRoomsDTO(
            occupiedRoom.getOccupiedRoomId(),
            occupiedRoom.getRoomId(),
            occupiedRoom.getStaffId(),
            occupiedRoom.getGuestId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOccupiedRoom(@PathVariable("id") Long idOccupiedRoom) {
        occupRoomSer.deleteOccupiedRoom(idOccupiedRoom);
        return ResponseEntity.ok("Operation with ID = " + idOccupiedRoom + " was deleted");
    }
}
