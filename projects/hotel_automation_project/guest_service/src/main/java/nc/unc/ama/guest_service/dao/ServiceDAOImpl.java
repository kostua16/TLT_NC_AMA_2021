package nc.unc.ama.guest_service.dao;

import nc.unc.ama.guest_service.entity.Service;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Service> getAllServices() {

        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Service", Service.class)
                .getResultList();
    }

    @Override
    public void deleteService(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query<Service> query = session.createQuery("delete from Service " + "where id =:serviceId");
        query.setParameter("serviceId", id);
        query.executeUpdate();
    }

    @Override
    public void saveService(Service service) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(service);
    }

    @Override
    public Service getService(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Service.class, id);

    }
}
