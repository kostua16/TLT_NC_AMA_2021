package nc.unc.ama.operation_service.service;

import nc.unc.ama.common.dto.BookingDTO;
import nc.unc.ama.common.dto.BookingREST;
import nc.unc.ama.common.dto.StaffDTO;
import nc.unc.ama.common.dto.StaffREST;
import nc.unc.ama.operation_service.dao.OccupiedRoomRepo;
import nc.unc.ama.operation_service.entity.OccupiedRoom;
import nc.unc.ama.operation_service.err.OccupiedRoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class OccupiedRoomService {

    private final OccupiedRoomRepo occupiedRoomRepo;
    private final BookingREST bookingREST;
    private final StaffREST staffREST;

    @Autowired
    public OccupiedRoomService(OccupiedRoomRepo occupiedRoomRepo, BookingREST bookingREST, StaffREST staffREST) {
        this.occupiedRoomRepo = occupiedRoomRepo;
        this.bookingREST = bookingREST;
        this.staffREST = staffREST;
    }

    public List<OccupiedRoom> getAllOccupiedRooms() {
        return occupiedRoomRepo.findAll();
    }

    public OccupiedRoom getOccupiedRoom(Long idOccupiedRoom) {
        return occupiedRoomRepo.findById(idOccupiedRoom).orElseThrow(()->new OccupiedRoomNotFoundException(idOccupiedRoom));
    }

    public OccupiedRoom addOccupiedRoom(Long guestId, Long staffTypeId) {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        BookingDTO bookingDTO = bookingREST.getBookingByGuest(guestId, today);
        StaffDTO staffDTO = staffREST.getRandomStaff(staffTypeId);
        OccupiedRoom occupiedRoom =  OccupiedRoom.builder()
            .guestId(bookingDTO.getGuestId())
            .roomId(bookingDTO.getRoomId())
            .staffId(staffDTO.getStaffId())
            .build();
        return occupiedRoomRepo.save(occupiedRoom);
    }

    public OccupiedRoom updateOccupiedRoom(OccupiedRoom occupiedRoomUpd, Long idOccupiedRoom) {
        occupiedRoomUpd.setOccupiedRoomId(idOccupiedRoom);
        return  occupiedRoomRepo.save(occupiedRoomUpd);
    }

    public void deleteOccupiedRoom(Long guestId) {
        Long occupiedRoom = occupiedRoomRepo.findOccupiedRoomByGuestId(guestId);
        occupiedRoomRepo.deleteById(occupiedRoom);
    }
}
