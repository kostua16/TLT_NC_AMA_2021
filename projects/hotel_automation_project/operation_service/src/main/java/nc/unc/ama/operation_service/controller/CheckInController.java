package nc.unc.ama.operation_service.controller;

import nc.unc.ama.common.dto.OccupiedRoomsDTO;
import nc.unc.ama.operation_service.entity.OccupiedRoom;
import nc.unc.ama.operation_service.service.OccupiedRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/check-in")
public class CheckInController {

    private final OccupiedRoomService roomService;

    @Autowired
    public CheckInController(OccupiedRoomService roomService) {
        this.roomService = roomService;
    }




    @PostMapping("/{id}")
    public ResponseEntity<OccupiedRoomsDTO> checkInGuest(@PathVariable("id") Long guestId,
                                                         @RequestParam(required = false, name = "staff-type-id") Long staffTypeId){
        OccupiedRoom occupiedRoom = roomService.addOccupiedRoom(guestId,staffTypeId);
        return ResponseEntity.ok(OccupiedRoomsDTO
            .builder()
            .occupiedRoomId(occupiedRoom.getOccupiedRoomId())
            .staffId(occupiedRoom.getStaffId())
            .guestId(occupiedRoom.getGuestId())
            .roomId(occupiedRoom.getRoomId())
            .build()
        );
    }
}
