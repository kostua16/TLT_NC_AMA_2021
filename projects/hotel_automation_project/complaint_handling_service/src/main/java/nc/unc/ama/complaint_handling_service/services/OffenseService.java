package nc.unc.ama.complaint_handling_service.services;

import nc.unc.ama.complaint_handling_service.entities.Offense;
import nc.unc.ama.complaint_handling_service.err.OffenseNotFoundException;
import nc.unc.ama.complaint_handling_service.repositories.OffenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OffenseService {
    private final OffenseRepo offenseRepo;

    @Autowired
    public OffenseService(OffenseRepo offenseRepo) {
        this.offenseRepo = offenseRepo;
    }
    @Transactional
    public Offense createOffense(Offense offense) {
        return offenseRepo.save(offense);
    }

    public Offense getOffense(Long offenseId) {
        return offenseRepo.findById(offenseId).orElseThrow(()->new OffenseNotFoundException(offenseId));
    }

    public List<Offense> getAllOffenses() {
        return offenseRepo.findAll();
    }

    @Transactional
    public Offense offenseUpdate(Offense offenseUpd, Long offenseId) {
        offenseUpd.setOffenseId(offenseId);
        return offenseRepo.save(offenseUpd);
    }

    @Transactional
    public void deleteOffense(Long offenseId) {
        offenseRepo.deleteById(offenseId);
    }
}
