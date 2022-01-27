package nc.unc.ama.test_spring_app.dto;

import javax.validation.constraints.NotBlank;

public class UserInfoDTO {

    @NotBlank
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
