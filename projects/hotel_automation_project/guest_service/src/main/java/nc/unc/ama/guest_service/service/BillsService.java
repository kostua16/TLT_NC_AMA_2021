package nc.unc.ama.guest_service.service;

import nc.unc.ama.guest_service.dao.BillsRepository;
import nc.unc.ama.guest_service.entity.Bills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillsService {

    private final BillsRepository billsRepository;

    @Autowired
    public BillsService(BillsRepository billsRepository) {
        this.billsRepository = billsRepository;
    }

    public List<Bills> getAllBills(){
        return billsRepository.findAll();
    }
}
