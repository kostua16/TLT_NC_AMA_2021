package nc.unc.ama.booking_service.services;

import nc.unc.ama.booking_service.entities.OccupiedRooms;
import nc.unc.ama.booking_service.repositories.OccupiedRoomsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OccupiedRoomsService {
    private final OccupiedRoomsRepo occupiedRoomsRepo;

    @Autowired
    public OccupiedRoomsService(OccupiedRoomsRepo occupiedRoomsRepo)
    {
        this.occupiedRoomsRepo = occupiedRoomsRepo;
    }

    public OccupiedRooms getOccumpiedRooms (Long occupiedRoomsId)
    {
        return occupiedRoomsRepo.findById(occupiedRoomsId).get();
    }
}
