package nc.unc.ama.complaint_handling_service.err;

public class OffenseNotFoundException extends RuntimeException{

    public OffenseNotFoundException(Long offenseId) {
        super(String.format("Offense with id = '%s' not found", offenseId));
    }
}
