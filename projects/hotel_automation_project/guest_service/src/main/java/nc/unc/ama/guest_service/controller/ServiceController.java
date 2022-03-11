package nc.unc.ama.guest_service.controller;

import nc.unc.ama.guest_service.entity.Service;
import nc.unc.ama.guest_service.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/services")
    public List<Service> showAllServices(){
        return serviceService.getAllServices();
    }

    @GetMapping("/services/{id}")
    public Service getService (@PathVariable int id){
        return serviceService.getService(id);
    }

    @PostMapping("/services")
    public Service addNewService (@RequestBody Service service){
        serviceService.saveService(service);
        return service;
    }

    @DeleteMapping("/services/{id}")
    public String deleteService (@PathVariable int id){
        serviceService.deleteService(id);
        return "Service with ID = " + id + " was deleted";
    }

    @PutMapping("/services")
    public Service updateService(@RequestBody Service service){
        serviceService.saveService(service);
        return service;
    }
}
