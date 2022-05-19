package nc.unc.ama.common.dto;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "guest", path = "/guests")
@ConditionalOnProperty(prefix = "app.clients", name = "guestsApi")
@Deprecated
public interface GuestREST {

    @GetMapping(path = "/{id}")
    ResponseEntity<GuestDTO> getGuest(@PathVariable("id") Long guestId) ;

    /*@PostMapping(path = "/")
    ResponseEntity<GuestDTO> guestReg(@RequestBody GuestCreationDTO guestDTO);*/
}
