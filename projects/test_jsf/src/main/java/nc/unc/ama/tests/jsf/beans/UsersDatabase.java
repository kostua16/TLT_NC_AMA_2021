package nc.unc.ama.tests.jsf.beans;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import nc.unc.ama.tests.jsf.entities.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bean
 */
@Named
@ApplicationScoped
@ManagedBean
public class UsersDatabase {


  private static final Logger LOG = LoggerFactory.getLogger(UsersDatabase.class);

  @Inject
  private JPA jpa;

  public UsersDatabase() {

  }

  @Inject
  public UsersDatabase(JPA jpa) {
    this.jpa = jpa;
  }

  @PostConstruct
  public void initialize() {
    this.addUser("admin", "admin");
  }

  public EntityManager getEntites() {
    return this.jpa.getManager();
  }

  /**
   * Добавление нового пользователя.
   * @param name Имя пользователя.
   * @param password Пароль пользователя.
   */
  public boolean addUser(final String name, final String password) {
    final boolean result;
    final EntityManager entites = this.getEntites();
    if (findUser(name, entites) == null) {
      final EntityTransaction transaction = entites.getTransaction();
      transaction.begin();
      final UserInfo newUser = new UserInfo(name, this.encryptPassword(password));
      entites.persist(newUser);
      transaction.commit();
      if (LOG.isInfoEnabled()) {
        LOG.info(String.format("User [%s] was created", name));
      }
      result = true;
    } else {
      if (LOG.isInfoEnabled()) {
        LOG.info(String.format("User [%s] already exists", name));
      }
      result = false;
    }
    return result;
  }

  protected String encryptPassword(String password) {
    try {
      MessageDigest md5hash = MessageDigest.getInstance("MD5");
      return new String(md5hash.digest(password.getBytes(StandardCharsets.UTF_8)),
        StandardCharsets.UTF_8);
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException("MD5 not found", e);
    }
  }


  /**
   * Поиск пользователя в базе данных.
   * @param name Имя пользователя.
   * @param entites Сессия подключения к базе данных.
   * @return Найденный пользователь.
   */
  private UserInfo findUser(final String name, EntityManager entites) {
    return entites.find(UserInfo.class, name);
  }

  /**
   * Поиск пользователя в базе данных.
   * @param name Имя пользователя.
   * @return Найденный пользователь.
   */
  public UserInfo findUser(final String name) {
    return this.findUser(name, this.getEntites());
  }

  /**
   * Проверяем авторизацию пользователя и фиксируем неудачные попытки. После 5 неудачных попыток
   * блокируем.
   * @param name Имя пользователя.
   * @param password Пароль пользователя.
   * @return Результат авторизации.
   */
  public boolean testUser(final String name, final String password) {
    boolean result = false;
    final EntityManager entites = this.getEntites();
    final UserInfo user = this.findUser(name, entites);
    if (user != null) {
      final EntityTransaction transaction = entites.getTransaction();
      transaction.begin();
      if (user.getWrong() < 5 && this.encryptPassword(password).equals(user.getPassword())) {
        user.setSuccess(user.getSuccess() + 1);
        user.setWrong(0);
        result = true;
      } else {
        user.setWrong(user.getWrong() + 1);
      }
      entites.persist(user);
      transaction.commit();
    }

    return result;
  }
}
