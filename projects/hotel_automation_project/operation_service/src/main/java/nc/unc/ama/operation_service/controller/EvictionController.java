package nc.unc.ama.operation_service.controller;

import java.util.UUID;
import nc.unc.ama.operation_service.service.OccupiedRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/eviction")
public class EvictionController {

    private final OccupiedRoomService roomService;

    @Autowired
    public EvictionController(OccupiedRoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> eviction(@PathVariable("id") UUID guestId){
        roomService.deleteOccupiedRoom(guestId);
        return ResponseEntity.ok("Guest with ID = " + guestId + " has been evicted");
    }
}
