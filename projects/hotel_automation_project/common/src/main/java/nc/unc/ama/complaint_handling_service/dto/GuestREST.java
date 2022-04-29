package nc.unc.ama.complaint_handling_service.dto;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "guest", path = "/guests")
@ConditionalOnProperty(prefix = "app.clients", name = "guestsApi")
public interface GuestREST {

    @GetMapping("/getGuest")
    GuestDTO getGuest();
}
