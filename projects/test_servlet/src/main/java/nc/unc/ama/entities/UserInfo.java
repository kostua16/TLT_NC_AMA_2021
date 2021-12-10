package nc.unc.ama.entities;

public class UserInfo {
  private String name;
  private String password;

  private int success = 0;

  private int wrong = 0;

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
