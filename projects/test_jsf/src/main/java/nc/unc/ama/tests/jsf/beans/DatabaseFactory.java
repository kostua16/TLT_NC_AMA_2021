package nc.unc.ama.tests.jsf.beans;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean
@ApplicationScoped
public class DatabaseFactory {
//  private Connection conn;
  private EntityManagerFactory sessionFactory;

  @PostConstruct
  public void initialize() {
//    this.conn = DriverManager.getConnection("jdbc:h2:~/testDatabase.db", "sa", "sa");
    this.sessionFactory = Persistence.createEntityManagerFactory("nc.unc.ama.tests.jsf");
  }

  @Produces
  @RequestScoped
  public EntityManager createEntityManager() {
    return this.sessionFactory.createEntityManager();
  }

  @PreDestroy
  public void finish() {
    if (this.sessionFactory != null) {
      this.sessionFactory.close();
    }
  }
}
