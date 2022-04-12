package nc.unc.ama.complaint_handling_service.dto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "guest", path = "/guests")
public interface GuestREST {

    @GetMapping("/getGuest")
    GuestDTO getGuest();
}
