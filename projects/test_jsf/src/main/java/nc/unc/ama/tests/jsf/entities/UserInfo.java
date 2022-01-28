package nc.unc.ama.tests.jsf.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Table(name = "app_users")
@Entity
public class UserInfo implements Serializable {
  
  @Column(name = "username")
  @Id
  private String name;
  
  @Column(name = "password")
  private String password;

  @Column(name = "logins")
  private int success = 0;

  @Column(name = "fails")
  private int wrong = 0;

  public UserInfo() {
  }

  public UserInfo(String name, String password) {
    this.name = name;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getSuccess() {
    return success;
  }

  public void setSuccess(int success) {
    this.success = success;
  }

  public int getWrong() {
    return wrong;
  }

  public void setWrong(int wrong) {
    this.wrong = wrong;
  }

  @Override public String toString() {
    return "UserInfo{" + "name='" + name + '\'' +
      ", password='" + password + '\'' +
      ", success=" + success +
      ", wrong=" + wrong +
      '}';
  }
}
