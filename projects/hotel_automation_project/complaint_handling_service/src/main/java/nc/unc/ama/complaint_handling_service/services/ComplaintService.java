package nc.unc.ama.complaint_handling_service.services;


import nc.unc.ama.common.dto.StaffREST;
import nc.unc.ama.complaint_handling_service.entities.Complaint;
import nc.unc.ama.complaint_handling_service.entities.Offense;
import nc.unc.ama.complaint_handling_service.repositories.ComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ComplaintService {
    private final ComplaintRepo complaintRepo;
    private final OffenseService offenseService;
    private final StaffREST staffREST;

    @Autowired
    public ComplaintService(ComplaintRepo complaintRepo, OffenseService offenseService, StaffREST staffREST) {

        this.complaintRepo = complaintRepo;
        this.offenseService = offenseService;
        this.staffREST = staffREST;
    }

    public Complaint getComplaint(Long complaintId) {

        return complaintRepo.findById(complaintId).get();
    }

    @Transactional
    public Complaint createComplain(Complaint complaint){
        Offense offense = offenseService.getOffense(complaint.getOffenseId());
        staffREST.changeRating(complaint.getStaffMemberId(),offense.getPoints(),false);
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
