package nc.unc.ama.complaint_handling_service.dto;


import org.springframework.web.bind.annotation.GetMapping;


public interface OccupiedRoomsREST {

    @GetMapping("/getOccupiedRoom")
    OccupiedRoomsDTO getOccupiedRoom();
}
