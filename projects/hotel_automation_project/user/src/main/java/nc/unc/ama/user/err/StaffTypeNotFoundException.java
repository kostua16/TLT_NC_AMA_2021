package nc.unc.ama.user.err;

public class StaffTypeNotFoundException extends RuntimeException{

    public StaffTypeNotFoundException(Long staffTypeId) {
        super(String.format("Staff type with id = '%s' not found", staffTypeId));
    }
}
