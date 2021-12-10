package nc.unc.ama.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import nc.unc.ama.entities.UserInfo;

public final class UsersDatabase {

  private static final UsersDatabase INSTANCE = new UsersDatabase();

  private final List<UserInfo> users;

  private UsersDatabase() {
    this.users = new ArrayList<>();
    this.addUser("admin", "admin");
  }

  /**
   * Добавление нового пользователя.
   * @param name Имя пользователя.
   * @param password Пароль пользователя.
   */
  public void addUser(final String name, final String password) {
    this.users.add(new UserInfo(name, this.encryptPassword(password)));
  }

  protected String encryptPassword(String password) {
    try {
      MessageDigest md5hash = MessageDigest.getInstance("MD5");
      return new String(md5hash.digest(password.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException("MD5 not found", e);
    }
  }

  /**
   * Поиск пользователя в базе данных.
   * @param name Имя пользователя.
   * @return Найденный пользователь.
   */
  public UserInfo findUser(final String name) {
    UserInfo result = null;
    for (UserInfo user : this.users) {
      if (name.equals(user.getName())) {
        result = user;
      }
    }
    return result;
  }

  /**
   * Проверяем авторизацию пользователя и фиксируем неудачные попытки. После 5 неудачных попыток
   * блокируем.
   * @param name Имя пользователя.
   * @param password Пароль пользователя.
   * @return Результат авторизации.
   */
  public boolean testUser(final String name, final String password) {
    final UserInfo user = this.findUser(name);
    boolean result = false;
    if (user != null) {
      if (user.getWrong() < 5 && this.encryptPassword(password).equals(user.getPassword())) {
        user.setSuccess(user.getSuccess() + 1);
        user.setWrong(0);
        result = true;
      } else {
        user.setWrong(user.getWrong() + 1);
      }
    }
    return result;
  }

  /**
   * Доступ к базе данных.
   * @return База данных.
   */
  public static UsersDatabase getInstance() {
    return INSTANCE;
  }
}
