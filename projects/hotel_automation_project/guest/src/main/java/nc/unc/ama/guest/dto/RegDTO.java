package nc.unc.ama.guest.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class RegDTO {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String phone;

    public RegDTO(String firstName, String lastName, String email, String password, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}
