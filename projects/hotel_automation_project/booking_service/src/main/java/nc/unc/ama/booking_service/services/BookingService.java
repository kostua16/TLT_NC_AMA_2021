package nc.unc.ama.booking_service.services;

import nc.unc.ama.booking_service.entities.Booking;
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
        return bookingRepo.findById(bookId).get();
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @Transactional
    public void updateBooking(Booking updatedBooking, Long bookId) {
        bookingRepo.findById(bookId)
            .map(booking -> {
                booking.setGuestId(updatedBooking.getGuestId());
                booking.setRoomId(updatedBooking.getRoomId());
                booking.setCheckInDate(updatedBooking.getCheckInDate());
                booking.setEvictionDate(updatedBooking.getEvictionDate());
                booking.setBookingCost(updatedBooking.getBookingCost());
                return bookingRepo.save(booking);
            })
            .orElseGet(() -> {
                updatedBooking.setBookingId(bookId);
                return bookingRepo.save(updatedBooking);
            });

    }

    public void deleteBooking(Long bookId) {
        bookingRepo.deleteById(bookId);
    }

    @Transactional
    public void bookRoom(Booking booking) {
        bookingRepo.save(booking);
    }

    public List<Booking> getFreeRooms(Date fromDate, Date toDate) {
        return bookingRepo.findBookingBetweenDates(fromDate,toDate);
    }
}
