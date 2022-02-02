package nc.unc.ama.tests.jsf.beans;


import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@ManagedBean
@RequestScoped
public class JPA  {

  private DatabaseFactory factory;

  private EntityManager manager;

  public JPA() {

  }

  @Inject
  public JPA(DatabaseFactory factory) {
    this.factory = factory;
  }

  @PostConstruct
  public void init() {
    if (factory!=null) {
      this.manager = this.factory.createEntityManager();
    }
  }

  @PreDestroy
  public void close() {
    if (this.manager!=null) {
      this.manager.close();
    }
  }

  public EntityManager getManager() {
    return manager;
  }
}
