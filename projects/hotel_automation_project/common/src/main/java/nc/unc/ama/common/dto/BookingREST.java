package nc.unc.ama.common.dto;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Calendar;
import java.util.List;

@FeignClient(name = "BOOKING", path = "/api/booking")
@ConditionalOnProperty(prefix = "app.clients", name = "bookingApi")
public interface BookingREST {

    @PostMapping(path = "/")
    ResponseEntity<BookingDTO> bookRoom(@RequestBody BookingCreateDTO bookingDTO);

    @GetMapping(path = "/{id}")
    ResponseEntity<BookingDTO> getBooking(@PathVariable("id") Long bookId);

    @GetMapping(path = "/")
    ResponseEntity<List<BookingDTO>> getAllBookings();

    @GetMapping(path = "/get-by-guest/{id}")
    BookingDTO getBookingByGuest(@PathVariable("id") Long guestId, Calendar today);

    @PutMapping(path = "/{id}")
    ResponseEntity<BookingDTO> updateBooking(@PathVariable("id") Long bookId, @RequestBody BookingDTO bookingDTO);

    @DeleteMapping(path="/{id}")
    ResponseEntity<String> deleteRoom(@PathVariable("id") Long bookId);
}
