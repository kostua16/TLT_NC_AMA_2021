package nc.unc.ama.booking_service.controllers;

import nc.unc.ama.booking_service.entities.Booking;
import nc.unc.ama.booking_service.entities.HotelRoom;
import nc.unc.ama.booking_service.services.BookingService;
import nc.unc.ama.common.dto.BookingCreateDTO;
import nc.unc.ama.common.dto.BookingDTO;
import nc.unc.ama.common.dto.BookingREST;
import nc.unc.ama.common.dto.HotelRoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="/api/booking")
public class BookingController implements BookingREST {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
//нельзя сделать две брони на одно и то же число
    @Override
    @PostMapping(path = "/")
    public ResponseEntity<BookingDTO> bookRoom(@RequestBody BookingCreateDTO bookingDTO){
        final Booking booking = bookingService.bookRoom(Booking
                .builder()
                .roomId(bookingDTO.getRoomId())
                .guestId(bookingDTO.getGuestId())
                .checkInDate(bookingDTO.getCheckInDate())
                .evictionDate(bookingDTO.getEvictionDate())
                .build()
        );
        return  ResponseEntity.ok(BookingDTO.builder()
            .bookingId(booking.getBookingId())
            .roomId(booking.getRoomId())
            .guestId(booking.getGuestId())
            .checkInDate(booking.getCheckInDate())
            .evictionDate(booking.getEvictionDate())
            .build());
    }

    @GetMapping(path = "/get-free-rooms")
    public ResponseEntity<List<HotelRoomDTO>> getFreeRooms(@RequestParam(required = false, name = "fromDate") @DateTimeFormat(pattern="MMddyyyy") Date fromDate,
                                                         @RequestParam(required = false, name = "toDate") @DateTimeFormat(pattern="MMddyyyy") Date toDate,
                                                         @RequestParam(required = false, name = "ordeByCostMin") Boolean costMin,
                                                           @RequestParam(required = false, name = "ordeByCostMax") Boolean costMax
                                                         ){
        List<HotelRoomDTO> roomDTOS = new ArrayList<>();
        for (HotelRoom room : bookingService.getFreeRooms(fromDate,toDate, costMin, costMax)) {
            roomDTOS.add(new HotelRoomDTO(
                room.getRoomId(),
                room.getRoomType(),
                room.getRoomBed(),
                room.getRoomCost()
            ));
        }
        return ResponseEntity.ok(roomDTOS);
    }
    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable("id") Long bookId)
    {
        Booking newBooking = bookingService.getBooking(bookId);
        return ResponseEntity.ok(new BookingDTO(
            newBooking.getBookingId(),
            newBooking.getGuestId(),
            newBooking.getRoomId(),
            newBooking.getCheckInDate(),
            newBooking.getEvictionDate()
        ));
    }
    @Override
    @GetMapping(path = "/")
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookingDTOList = new ArrayList<>();
        for (Booking booking : bookingService.getAllBookings()) {
            bookingDTOList.add(new BookingDTO(
                booking.getBookingId(),
                booking.getGuestId(),
                booking.getRoomId(),
                booking.getCheckInDate(),
                booking.getEvictionDate()
            ));
        }
        return ResponseEntity.ok(bookingDTOList);
    }
    @Override
    @GetMapping(path = "/get-by-guest/{id}")
    public BookingDTO getBookingByGuest(@PathVariable("id") Long guestId, Calendar today){
        Booking newBooking = bookingService.getBookingByGuest(guestId, today);
        return new BookingDTO(
            newBooking.getBookingId(),
            newBooking.getGuestId(),
            newBooking.getRoomId(),
            newBooking.getCheckInDate(),
            newBooking.getEvictionDate()
        );
    }
    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable("id") Long bookId, @RequestBody BookingDTO bookingDTO){
        Booking booking = bookingService.updateBooking(Booking
                .builder()
                .bookingId(bookingDTO.getBookingId())
                .roomId(bookingDTO.getRoomId())
                .guestId(bookingDTO.getGuestId())
                .checkInDate(bookingDTO.getCheckInDate())
                .evictionDate(bookingDTO.getEvictionDate())
                .build(),
            bookId
        );
        return ResponseEntity.ok(BookingDTO.builder()
            .bookingId(booking.getBookingId())
            .roomId(booking.getRoomId())
            .guestId(booking.getGuestId())
            .checkInDate(booking.getCheckInDate())
            .evictionDate(booking.getEvictionDate())
            .build());
    }

    @Override
    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") Long bookId){
        bookingService.deleteBooking(bookId);
        return ResponseEntity.ok("Operation with ID = " + bookId + " was deleted");
    }
}
