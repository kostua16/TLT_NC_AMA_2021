package nc.unc.ama.complaint_handling_service.controllers;

import nc.unc.ama.complaint_handling_service.dto.OccupiedRoomsDTO;
import nc.unc.ama.complaint_handling_service.dto.OccupiedRoomsREST;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OccupiedRoomsControllerImpl implements OccupiedRoomsREST {

    @Override
    @GetMapping("/getOccupiedRoom")
    public OccupiedRoomsDTO getOccupiedRoom(){
        return new OccupiedRoomsDTO();
    }
}
