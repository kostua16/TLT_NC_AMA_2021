package nc.unc.ama.common.dto;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "guest", path = "/guests")
@ConditionalOnProperty(prefix = "app.clients", name = "guestsApi")
public interface GuestREST {

    @GetMapping(path = "/{id}")
    GuestDTO getGuest(@PathVariable("id") Long guestId) ;

    @PostMapping(path = "/")
    ResponseEntity<GuestDTO> guestReg(@RequestBody GuestDTO guestDTO);
}
