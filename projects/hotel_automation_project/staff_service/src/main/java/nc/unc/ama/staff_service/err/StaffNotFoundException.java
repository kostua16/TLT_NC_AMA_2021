package nc.unc.ama.staff_service.err;

public class StaffNotFoundException extends RuntimeException{

    public StaffNotFoundException(Long id) {
        super(String.format("Staff member with id = '%s' not found", id));
    }
}
