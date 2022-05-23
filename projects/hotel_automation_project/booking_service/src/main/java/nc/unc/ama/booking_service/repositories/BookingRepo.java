package nc.unc.ama.booking_service.repositories;

import nc.unc.ama.booking_service.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
    @Query("SELECT distinct b.roomId FROM Booking b WHERE (?1 between b.checkInDate and b.evictionDate) " +
        "or (?2 between b.checkInDate and b.evictionDate)" +
        "or ((b.checkInDate between ?1 and ?2) and (b.evictionDate between ?1 and ?2)) ")
    List<Long> findBookingBetweenDates(Date fromDate, Date toDate);

    @Query("SELECT b FROM Booking b WHERE b.guestId = ?1 and b.checkInDate = ?2")
    Booking findBookingByGuestId(UUID guestId, Calendar today);

    Booking findBookingByCheckInDateAndEvictionDateAndRoomId(Date fromDate, Date toDate, Long roomId);

}
