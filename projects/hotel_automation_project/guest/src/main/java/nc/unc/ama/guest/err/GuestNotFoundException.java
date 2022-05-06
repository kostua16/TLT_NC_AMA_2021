package nc.unc.ama.guest.err;

public class GuestNotFoundException extends RuntimeException{

    public GuestNotFoundException(Long guestId) {
        super(String.format("Guest with id = '%s' not found", guestId));
    }

}
