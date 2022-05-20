package nc.unc.ama.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class UserRegistrationDTO {

    private String email;

    private long phone;

    private String firstName;

    private String lastName;

    private String password;
}
