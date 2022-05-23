package nc.unc.ama.complaint_handling_service.services;


import java.util.List;
import feign.FeignException;
import nc.unc.ama.common.dto.UsersREST;
import nc.unc.ama.common.err.UserCantBeUpdatedException;
import nc.unc.ama.complaint_handling_service.entities.Complaint;
import nc.unc.ama.complaint_handling_service.entities.Offense;
import nc.unc.ama.complaint_handling_service.repositories.ComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ComplaintService {

    private final ComplaintRepo complaintRepo;

    private final OffenseService offenseService;

    private final UsersREST usersREST;

    @Autowired
    public ComplaintService(ComplaintRepo complaintRepo, OffenseService offenseService,
                            UsersREST usersREST) {

        this.complaintRepo = complaintRepo;
        this.offenseService = offenseService;
        this.usersREST = usersREST;
    }

    public Complaint getComplaint(Long complaintId) {

        return complaintRepo.findById(complaintId).get();
    }

    @Transactional
    public Complaint createComplain(Complaint complaint) {
        Offense offense = offenseService.getOffense(complaint.getOffenseId());
        try {
            this.usersREST.rateSet(complaint.getStaffMemberId(), offense.getPoints());
        } catch (FeignException.FeignClientException.BadRequest badRequest) {
            throw new UserCantBeUpdatedException(
                complaint.getStaffMemberId(),
                "Cant rate staff",
                badRequest
            );
        }
        return complaintRepo.save(complaint);
    }//отправить оповещение администратору и работнику на почту
    //добавить тип проступка, в нём будет цена ошибки сотрудника вылияющая на рейтинг
    //вызывается стафсервис в котором отнимается рейтинг у сотрудника

    public List<Complaint> getAllComplaints() {
        return complaintRepo.findAll();
    }

    public List<Complaint> getComplaintByStaffId(Long staffMemberId) {
        return complaintRepo.findComplaintByStaffMemberId(staffMemberId);
    }
    //сотрудник или администратор узнают жалобы на конкретного сотрудника
}
