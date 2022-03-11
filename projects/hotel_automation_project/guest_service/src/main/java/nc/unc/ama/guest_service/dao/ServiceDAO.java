package nc.unc.ama.guest_service.dao;

import nc.unc.ama.guest_service.entity.Service;

import java.util.List;

public interface ServiceDAO {

    List<Service> getAllServices();
    void deleteService (int id);
    void saveService (Service service);
    Service getService (int id);


}
