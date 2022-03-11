package nc.unc.ama.guest_service.service;

import nc.unc.ama.guest_service.entity.Service;

import java.util.List;

public interface ServiceService {
    List<Service> getAllServices();
    void deleteService (int id);
    void saveService (Service service);
    Service getService (int id);
}
