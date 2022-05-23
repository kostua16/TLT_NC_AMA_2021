package nc.unc.ama.operation_service.service;

import java.util.Calendar;
import java.util.List;
import nc.unc.ama.common.dto.BookingDTO;
import nc.unc.ama.common.dto.BookingREST;
import nc.unc.ama.common.dto.UserInfoDTO;
import nc.unc.ama.common.dto.UsersREST;
import nc.unc.ama.operation_service.dao.OccupiedRoomRepo;
import nc.unc.ama.operation_service.entity.OccupiedRoom;
import nc.unc.ama.operation_service.err.OccupiedRoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OccupiedRoomService {

    private final OccupiedRoomRepo occupiedRoomRepo;

    private final BookingREST bookingREST;

    private final UsersREST usersREST;

    @Autowired
    public OccupiedRoomService(OccupiedRoomRepo occupiedRoomRepo, BookingREST bookingREST,
                               UsersREST usersREST) {
        this.occupiedRoomRepo = occupiedRoomRepo;
        this.bookingREST = bookingREST;
        this.usersREST = usersREST;
    }

    public List<OccupiedRoom> getAllOccupiedRooms() {
        return occupiedRoomRepo.findAll();
    }

    public OccupiedRoom getOccupiedRoom(Long idOccupiedRoom) {
        return occupiedRoomRepo.findById(idOccupiedRoom).orElseThrow(() -> new OccupiedRoomNotFoundException(idOccupiedRoom));
    }

    public OccupiedRoom addOccupiedRoom(Long guestId, Long staffTypeId) {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        BookingDTO bookingDTO = bookingREST.getBookingByGuest(guestId, today);
        final UserInfoDTO staffMember = this.usersREST.listStaff().getBody().iterator().next();
        OccupiedRoom occupiedRoom = OccupiedRoom.builder()
            .guestId(bookingDTO.getGuestId())
            .roomId(bookingDTO.getRoomId())
            .staffId(staffMember.getId())
            .build();
        return occupiedRoomRepo.save(occupiedRoom);
    }

    public OccupiedRoom updateOccupiedRoom(OccupiedRoom occupiedRoomUpd, Long idOccupiedRoom) {
        occupiedRoomUpd.setOccupiedRoomId(idOccupiedRoom);
        return occupiedRoomRepo.save(occupiedRoomUpd);
    }

    public void deleteOccupiedRoom(Long guestId) {
        Long occupiedRoom = occupiedRoomRepo.findOccupiedRoomByGuestId(guestId);
        occupiedRoomRepo.deleteById(occupiedRoom);
    }
}
