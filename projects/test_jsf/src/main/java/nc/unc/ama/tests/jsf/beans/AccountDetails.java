package nc.unc.ama.tests.jsf.beans;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import nc.unc.ama.tests.jsf.entities.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@SessionScoped
@ManagedBean
public class AccountDetails implements Serializable {

  private static transient final Logger LOG = LoggerFactory.getLogger(AccountDetails.class);

  private String login;
  private String password;

  private String loginStatus;

  private String passwordStatus;

  private UserInfo userInfo;

  private transient UsersDatabase usersDatabase;

  private transient FacesContext facesContext;

  @Inject
  public AccountDetails(@Named("usersDatabase") UsersDatabase usersDatabase, FacesContext facesContext) {
    this.usersDatabase = usersDatabase;
    this.facesContext = facesContext;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getLoginStatus() {
    return loginStatus;
  }

  public void setLoginStatus(String loginStatus) {
    this.loginStatus = loginStatus;
  }

  public String getPasswordStatus() {
    return passwordStatus;
  }

  public void setPasswordStatus(String passwordStatus) {
    this.passwordStatus = passwordStatus;
  }

  public UserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(UserInfo userInfo) {
    this.userInfo = userInfo;
  }

  public void doLogout() {
    this.userInfo = null;
  }

  public void doLogin() {
    boolean fieldsValid = true;
    this.loginStatus = "is-valid";
    this.passwordStatus = "is-valid";
    FacesMessage loginMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "OK");
    FacesMessage passwordMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "OK");
    if (this.login.length() < 3) {
      loginMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong name", "Minimum length 3");
      this.loginStatus = "is-invalid";
      fieldsValid = false;
      if (LOG.isWarnEnabled()) {
        LOG.warn("Login - Minimum length 3 required");
      }
    }
    if (this.password.length() < 3) {
      passwordMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong password", "Minimum length 3");
      this.passwordStatus = "is-invalid";
      fieldsValid = false;
      if (LOG.isWarnEnabled()) {
        LOG.warn("Password - Minimum length 3 required");
      }
    }
    if (fieldsValid) {
      this.userInfo = this.usersDatabase.findUser(this.login);
      fieldsValid = this.usersDatabase.testUser(this.login, this.password);

      if (fieldsValid) {
        this.login = "";
        loginMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "User authorized");
        passwordMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "User authorized");
        if (LOG.isInfoEnabled()) {
          LOG.info(String.format("User [%s] authorized", this.login));
        }
      } else {
        if (this.userInfo ==null) {
          loginMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "User not found", "User not found");
          this.loginStatus = "is-invalid";
          if (LOG.isWarnEnabled()) {
            LOG.warn(String.format("User [%s] not found", this.login));
          }
        } else {
          passwordMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong password", "Wrong password");
          this.passwordStatus = "is-invalid";
          if (LOG.isWarnEnabled()) {
            LOG.warn(String.format("Wrong password tried for user [%s]", this.login));
          }
        }
      }
      this.password = "";
    }
    this.facesContext.addMessage("registerForm:registerLogin", loginMessage);
    this.facesContext.addMessage("registerForm:registerPassword", passwordMessage);

  }
}
