package project.Guest_Service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("service")
public class ServiceController {
    @GetMapping
    public String list (){
        return "Soon it will be possible to order services here";
    }
}
