package nc.unc.ama.guest_service.service;

import nc.unc.ama.complaint_handling_service.dto.OccupiedRoomsDTO;
import nc.unc.ama.complaint_handling_service.dto.OccupiedRoomsREST;
import nc.unc.ama.guest_service.dao.OcRoomsRepo;
import nc.unc.ama.guest_service.entity.OccupiedRooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OcRoomsService {

    private final OcRoomsRepo ocRoomsRepo;

    @Autowired
    public OcRoomsService(OcRoomsRepo ocRoomsRepo) {
        this.ocRoomsRepo = ocRoomsRepo;
    }

    public List<OccupiedRooms> getAllOcRooms(){
        return ocRoomsRepo.findAll();
    }

}
