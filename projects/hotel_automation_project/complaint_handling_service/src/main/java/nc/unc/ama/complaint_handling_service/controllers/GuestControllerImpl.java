package nc.unc.ama.complaint_handling_service.controllers;

import nc.unc.ama.complaint_handling_service.dto.GuestDTO;
import nc.unc.ama.complaint_handling_service.dto.GuestREST;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuestControllerImpl implements GuestREST {
    @Override
    @GetMapping("/getGuest")
    public GuestDTO getGuest(){
        return  new GuestDTO();
    }
}