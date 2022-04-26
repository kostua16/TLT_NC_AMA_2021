package nc.unc.ama.booking_service.err;

public class BookingNotFoundException extends RuntimeException{

    public BookingNotFoundException(Long bookId) {
        super(String.format("Booking with id = '%s' not found", bookId));
    }

}
