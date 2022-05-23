package nc.unc.ama.booking_service.err;

public class BookingAlredyExists extends RuntimeException{

    public BookingAlredyExists() {
        super("Booking alredy exists");
    }
}
