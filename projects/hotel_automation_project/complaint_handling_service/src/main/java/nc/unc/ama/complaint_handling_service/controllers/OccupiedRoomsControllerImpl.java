package nc.unc.ama.complaint_handling_service.controllers;

import nc.unc.ama.complaint_handling_service.dto.OccupiedRoomsDTO;
import nc.unc.ama.complaint_handling_service.dto.OccupiedRoomsREST;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OccupiedRoomsControllerImpl implements OccupiedRoomsREST {

    @Override
    @GetMapping("/get-occupied-room")
    public OccupiedRoomsDTO getOccupiedRoom(){
        return new OccupiedRoomsDTO();
    }
}
