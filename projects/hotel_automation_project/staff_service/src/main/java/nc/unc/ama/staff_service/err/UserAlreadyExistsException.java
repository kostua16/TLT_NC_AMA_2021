package nc.unc.ama.staff_service.err;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(final String userName) {
        super("User already exists: " + userName);
    }
}
