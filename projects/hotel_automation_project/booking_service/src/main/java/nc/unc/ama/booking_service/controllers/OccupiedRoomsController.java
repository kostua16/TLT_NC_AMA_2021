package nc.unc.ama.booking_service.controllers;

import nc.unc.ama.booking_service.entities.OccupiedRooms;
import nc.unc.ama.booking_service.services.OccupiedRoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/OccupiedRooms")
public class OccupiedRoomsController {

    private final OccupiedRoomsService occRoomsService;

    @Autowired
    public OccupiedRoomsController(OccupiedRoomsService occRoomsService)
    {
        this.occRoomsService = occRoomsService;
    }

    @PostMapping(path = "/bookRoom")
    public void bookRoom(@RequestBody OccupiedRooms occupiedRooms){

    }

    @GetMapping(path = "/viewOccupiedRoom/{occupiedRoomsId}")
    public OccupiedRooms viewOccumpiedRooms(@PathVariable("occupiedRoomsId") Long occupiedRoomsId)
    {
        return occRoomsService.getOccumpiedRooms(occupiedRoomsId);
    }
}
