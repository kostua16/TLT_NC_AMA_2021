package nc.unc.ama.guest_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("room")
public class HRController {
    @GetMapping
    public String list(){
        return "Working with Numbers in Development";
    }
}
