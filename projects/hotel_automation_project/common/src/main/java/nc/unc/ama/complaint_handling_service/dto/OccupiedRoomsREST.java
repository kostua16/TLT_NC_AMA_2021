package nc.unc.ama.complaint_handling_service.dto;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "booking", path = "/rooms")
@ConditionalOnProperty(prefix = "app.clients", name = "roomsApi")
public interface OccupiedRoomsREST {

    @GetMapping("/getOccupiedRoom")
    OccupiedRoomsDTO getOccupiedRoom();
}
