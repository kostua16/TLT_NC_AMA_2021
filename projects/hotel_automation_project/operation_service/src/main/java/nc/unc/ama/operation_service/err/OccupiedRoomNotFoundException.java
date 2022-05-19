package nc.unc.ama.operation_service.err;

public class OccupiedRoomNotFoundException extends RuntimeException{

    public OccupiedRoomNotFoundException(Long idOccupiedRoom) {
        super(String.format("Occupied room with id = '%s' not found", idOccupiedRoom));
    }
}
