package nc.unc.ama.test_spring_app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userInfoId;

    @NotBlank
    @Column(nullable = false, updatable = false, unique = true)
    private String login;

    @NotBlank
    @Column(nullable = false)
    private String password;

    private int success = 0;

    private int wrong = 0;

    public UserInfo(
        final String login,
        final String password
    ) {
        this.login = login;
        this.password = password;
    }

    public UserInfo() {
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

    @Override
    public String toString() {
        return "UserInfo{" + "login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", success=" + success +
            ", wrong=" + wrong +
            '}';
    }
}
