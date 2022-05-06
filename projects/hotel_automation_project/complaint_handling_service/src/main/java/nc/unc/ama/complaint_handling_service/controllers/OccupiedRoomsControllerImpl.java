package nc.unc.ama.complaint_handling_service.controllers;

import nc.unc.ama.common.dto.OccupiedRoomsDTO;
import nc.unc.ama.common.dto.OccupiedRoomsREST;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/rooms")
public class OccupiedRoomsControllerImpl implements OccupiedRoomsREST {

    @Override
    @GetMapping("/getOccupiedRoom")
    public OccupiedRoomsDTO getOccupiedRoom(){
        return new OccupiedRoomsDTO();
    }
}
