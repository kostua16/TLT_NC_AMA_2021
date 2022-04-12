package nc.unc.ama.complaint_handling_service.controllers;

import nc.unc.ama.complaint_handling_service.dto.GuestDTO;
import nc.unc.ama.complaint_handling_service.dto.GuestREST;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guests")
public class GuestControllerImpl implements GuestREST {
    @Override
    @GetMapping("/getGuest")
    public GuestDTO getGuest(){
        return  new GuestDTO();
    }
}
