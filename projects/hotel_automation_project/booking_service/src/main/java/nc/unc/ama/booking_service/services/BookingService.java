package nc.unc.ama.booking_service.services;

import nc.unc.ama.booking_service.entities.Booking;
import nc.unc.ama.booking_service.err.BookingNotFoundException;
import nc.unc.ama.booking_service.repositories.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepo bookingRepo;

    @Autowired
    public BookingService(BookingRepo bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    public Booking getBooking(Long bookId) {
        return bookingRepo.findById(bookId).orElseThrow(()-> new BookingNotFoundException(bookId));
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @Transactional
    public Booking updateBooking(Booking updatedBooking, Long bookId) {
        updatedBooking.setBookingId(bookId);
        return bookingRepo.save(updatedBooking);
    }
    @Transactional
    public void deleteBooking(Long bookId) {
        bookingRepo.deleteById(bookId);
    }

    @Transactional
    public Booking bookRoom(Booking booking) {
        return bookingRepo.save(booking);
    }

    public List<Booking> getFreeRooms(Date fromDate, Date toDate) {
        return bookingRepo.findBookingBetweenDates(fromDate,toDate);
    }
}
