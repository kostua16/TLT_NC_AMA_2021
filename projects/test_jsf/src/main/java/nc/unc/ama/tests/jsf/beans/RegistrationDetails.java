package nc.unc.ama.tests.jsf.beans;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@RequestScoped
@ManagedBean
public class RegistrationDetails {

  private static transient final Logger LOG = LoggerFactory.getLogger(RegistrationDetails.class);

  private String login;

  private String password;

  private String loginStatus;

  private String passwordStatus;

  private transient UsersDatabase usersDatabase;

  private transient FacesContext facesContext;

  @Inject
  public RegistrationDetails(@Named("usersDatabase") UsersDatabase usersDatabase,
                             FacesContext facesContext) {
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

  public void doRegister() {
    boolean fieldsValid = true;
    this.loginStatus = "is-valid";
    this.passwordStatus = "is-valid";
    FacesMessage loginMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "OK");
    FacesMessage passwordMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "OK");
    if (this.login.length() < 3) {
      loginMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong name", "Minimum length " +
        "3");
      this.loginStatus = "is-invalid";
      fieldsValid = false;
      if (LOG.isWarnEnabled()) {
        LOG.warn("Login - Minimum length 3 required");
      }
    }
    if (this.password.length() < 3) {
      passwordMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong password", "Minimum " +
        "length 3");
      this.passwordStatus = "is-invalid";
      fieldsValid = false;
      if (LOG.isWarnEnabled()) {
        LOG.warn("Password - Minimum length 3 required");
      }
    }
    if (fieldsValid) {
      fieldsValid = this.usersDatabase.addUser(this.login, this.password);
      if (fieldsValid) {
        this.login = "";
        loginMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "User created");
        passwordMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "User created");
        if (LOG.isInfoEnabled()) {
          LOG.info(String.format("User [%s] registered", this.login));
        }
      } else {
        loginMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Duplicate user", "User " +
          "already exists");
        passwordMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Duplicate user", "User " +
          "already exists");
        this.loginStatus = "is-invalid";
        this.passwordStatus = "is-invalid";
        if (LOG.isWarnEnabled()) {
          LOG.warn(String.format("User [%s] already exists", this.login));
        }
      }
      this.password = "";
    }
    this.facesContext.addMessage("registerForm:registerLogin", loginMessage);
    this.facesContext.addMessage("registerForm:registerPassword", passwordMessage);
  }
}
