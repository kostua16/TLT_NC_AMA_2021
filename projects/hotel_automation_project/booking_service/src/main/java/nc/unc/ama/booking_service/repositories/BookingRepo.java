package nc.unc.ama.booking_service.repositories;

import nc.unc.ama.booking_service.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b WHERE b.checkInDate<=?1 and b.evictionDate>=?2")
    List<Booking> findBookingBetweenDates(Date fromDate, Date toDate);
}
