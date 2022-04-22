package nc.unc.ama.booking_service.controllers;

import nc.unc.ama.booking_service.entities.Booking;
import nc.unc.ama.booking_service.services.BookingService;
import nc.unc.ama.complaint_handling_service.dto.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="/booking")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping(path = "/")
    public void bookRoom(@RequestBody BookingDTO bookingDTO){
        bookingService.bookRoom(Booking
                .builder()
                .bookingId(bookingDTO.getBookingId())
                .roomId(bookingDTO.getRoomId())
                .guestId(bookingDTO.getGuestId())
                .checkInDate(bookingDTO.getCheckInDate())
                .evictionDate(bookingDTO.getEvictionDate())
                .bookingCost(bookingDTO.getBookingCost())
                .build()
        );
    }

    @GetMapping(path = "/getFreeRooms")
    public List<BookingDTO> getFreeRooms(@RequestParam(required = false, name = "fromDate") @DateTimeFormat(pattern="MMddyyyy")
        Date fromDate, @RequestParam(required = false, name = "toDate") @DateTimeFormat(pattern="MMddyyyy") Date toDate){
        List<BookingDTO> bookingDTOList = new ArrayList<>();
        for (Booking booking : bookingService.getFreeRooms(fromDate,toDate)) {
            bookingDTOList.add(new BookingDTO(
                booking.getBookingId(),
                booking.getGuestId(),
                booking.getRoomId(),
                booking.getCheckInDate(),
                booking.getEvictionDate(),
                booking.getBookingCost()
            ));
        }
        return bookingDTOList;
    }
    @GetMapping(path = "{bookId}")
    public BookingDTO getBooking(@PathVariable("bookId") Long bookId)
    {
        Booking newBooking = bookingService.getBooking(bookId);
        return new BookingDTO(
            newBooking.getBookingId(),
            newBooking.getGuestId(),
            newBooking.getRoomId(),
            newBooking.getCheckInDate(),
            newBooking.getEvictionDate(),
            newBooking.getBookingCost()
        );
    }
    @GetMapping(path = "/")
    public List<BookingDTO> getAllBookings() {
        List<BookingDTO> bookingDTOList = new ArrayList<>();
        for (Booking booking : bookingService.getAllBookings()) {
            bookingDTOList.add(new BookingDTO(
                booking.getBookingId(),
                booking.getGuestId(),
                booking.getRoomId(),
                booking.getCheckInDate(),
                booking.getEvictionDate(),
                booking.getBookingCost()
            ));
        }
        return bookingDTOList;
    }
    @PutMapping(path = "/{bookId}")
    public void updateBooking(@PathVariable("bookId") Long bookId, @RequestBody BookingDTO bookingDTO){
        bookingService.updateBooking(Booking
                .builder()
                .bookingId(bookingDTO.getBookingId())
                .roomId(bookingDTO.getRoomId())
                .guestId(bookingDTO.getGuestId())
                .checkInDate(bookingDTO.getCheckInDate())
                .evictionDate(bookingDTO.getEvictionDate())
                .bookingCost(bookingDTO.getBookingCost())
                .build(),
            bookId
        );
    }

    @DeleteMapping(path="/{bookId}")
    public void deleteRoom(@PathVariable("bookId") Long bookId){
        bookingService.deleteBooking(bookId);
    }
}
