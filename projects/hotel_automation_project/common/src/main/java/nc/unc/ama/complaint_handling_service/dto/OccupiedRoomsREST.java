package nc.unc.ama.complaint_handling_service.dto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "booking", path = "/rooms")
public interface OccupiedRoomsREST {

    @GetMapping("/getOccupiedRoom")
    OccupiedRoomsDTO getOccupiedRoom();
}
