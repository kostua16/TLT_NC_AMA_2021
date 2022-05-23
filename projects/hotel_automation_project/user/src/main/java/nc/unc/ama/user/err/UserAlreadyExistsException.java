package nc.unc.ama.user.err;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(final String userName) {
        super("User already exists: " + userName);
    }
}
