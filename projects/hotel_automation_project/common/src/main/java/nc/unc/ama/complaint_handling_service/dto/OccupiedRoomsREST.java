package nc.unc.ama.complaint_handling_service.dto;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient()
@ConditionalOnProperty()
public interface OccupiedRoomsREST {

    @GetMapping("/")
    List<OccupiedRoomsDTO> getAllOccupiedRoom();
}
