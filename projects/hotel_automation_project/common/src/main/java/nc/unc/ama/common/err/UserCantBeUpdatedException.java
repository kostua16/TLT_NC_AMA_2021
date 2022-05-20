package nc.unc.ama.common.err;

import java.util.UUID;

@SuppressWarnings("PMD")
public class UserCantBeUpdatedException extends RuntimeException {

    private static final String MSG_TMPL = "User %s can't be updated: %s";

    public UserCantBeUpdatedException(UUID userId, String reason) {
        super(String.format(UserCantBeUpdatedException.MSG_TMPL, userId, reason));
    }

    public UserCantBeUpdatedException(UUID userId, String reason, Throwable exception) {
        super(String.format(UserCantBeUpdatedException.MSG_TMPL, userId, reason), exception);
    }
}
