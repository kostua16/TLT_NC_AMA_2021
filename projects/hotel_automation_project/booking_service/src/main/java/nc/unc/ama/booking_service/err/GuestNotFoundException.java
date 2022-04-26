package nc.unc.ama.booking_service.err;

public class GuestNotFoundException extends RuntimeException{

    public GuestNotFoundException(Long guestId) {
        super(String.format("Guest with id = '%s' not found", guestId));
    }

}
