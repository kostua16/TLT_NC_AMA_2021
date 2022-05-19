package nc.unc.ama.operation_service.service;

import nc.unc.ama.operation_service.dao.OccupiedRoomRepo;
import nc.unc.ama.operation_service.entity.OccupiedRoom;
import nc.unc.ama.operation_service.err.OccupiedRoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OccupiedRoomService {

    private final OccupiedRoomRepo occupiedRoomRepo;

    @Autowired
    public OccupiedRoomService(OccupiedRoomRepo occupiedRoomRepo) {
        this.occupiedRoomRepo = occupiedRoomRepo;
    }

    public List<OccupiedRoom> getAllOccupiedRooms() {
        return occupiedRoomRepo.findAll();
    }

    public OccupiedRoom getOccupiedRoom(Long idOccupiedRoom) {
        return occupiedRoomRepo.findById(idOccupiedRoom).orElseThrow(()->new OccupiedRoomNotFoundException(idOccupiedRoom));
    }

    public OccupiedRoom addOccupiedRoom(OccupiedRoom occupiedRoom) {
        return occupiedRoomRepo.save(occupiedRoom);
    }

    public OccupiedRoom updateOccupiedRoom(OccupiedRoom occupiedRoomUpd, Long idOccupiedRoom) {
        occupiedRoomUpd.setOccupiedRoomId(idOccupiedRoom);
        return  occupiedRoomRepo.save(occupiedRoomUpd);
    }

    public void deleteOccupiedRoom(Long idOccupiedRoom) {
        occupiedRoomRepo.deleteById(idOccupiedRoom);
    }
}
