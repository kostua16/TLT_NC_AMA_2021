package nc.unc.ama.guest_service.controller;

import nc.unc.ama.complaint_handling_service.dto.OccupiedRoomsDTO;
import nc.unc.ama.complaint_handling_service.dto.OccupiedRoomsREST;
import nc.unc.ama.guest_service.entity.OccupiedRooms;
import nc.unc.ama.guest_service.service.OcRoomsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OcRoomsController implements OccupiedRoomsREST {

    private final OcRoomsService ocRoomsService;

    @Autowired
    public OcRoomsController(OcRoomsService ocRoomsService) {
        this.ocRoomsService = ocRoomsService;
    }

    @Override
    public List<OccupiedRoomsDTO> getAllOccupiedRoom() {

        List<OccupiedRoomsDTO> occupiedRoomsDTO = new ArrayList<>();

        for (OccupiedRooms occupiedRooms: ocRoomsService.getAllOcRooms()){
            occupiedRoomsDTO.add(new OccupiedRoomsDTO(
                occupiedRooms.getRoomId(),
                occupiedRooms.getOccupiedRoomId(),
                occupiedRooms.getStaffId()));
        }
        return occupiedRoomsDTO;
    }
}
