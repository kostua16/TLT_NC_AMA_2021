package nc.unc.ama.test_spring_app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserInfoDTO {

    @NotBlank(message = "Incorrect field")
    @Size(min = 6, max = 12, message = "Логин должен быть от 6 до 12 символов!")
    private String login;

    @NotBlank
    private String password;

    public UserInfoDTO(
        final String login,
        final String password
    ) {
        this.login = login;
//        this.password = AuthConfig.encryptPassword(password);
        this.password = password;
    }

    public UserInfoDTO() {
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
}
