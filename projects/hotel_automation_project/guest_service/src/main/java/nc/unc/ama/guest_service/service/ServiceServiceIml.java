package nc.unc.ama.guest_service.service;

import nc.unc.ama.guest_service.dao.ServiceDAO;
import nc.unc.ama.guest_service.entity.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceIml implements ServiceService{

    @Autowired
    private ServiceDAO serviceDAO;

    @Override
    @Transactional
    public List<Service> getAllServices() {
        return serviceDAO.getAllServices();
    }

    @Override
    @Transactional
    public void deleteService(int id) {serviceDAO.deleteService(id);}

    @Override
    @Transactional
    public void saveService(Service service) {serviceDAO.saveService(service);}

    @Override
    @Transactional
    public Service getService(int id) {return serviceDAO.getService(id);}
}
