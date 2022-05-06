package nc.unc.ama.staff_service.err;

public class StaffNotFoundException extends RuntimeException{

    public StaffNotFoundException(Long staffId) {
        super(String.format("Staff member with id = '%s' not found", staffId));
    }
}
