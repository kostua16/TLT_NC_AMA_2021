package nc.unc.ama.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@SuppressWarnings("PMD.ShortVariable")
public class UserUpdateDTO {

    private Long id;

    private String email;

    private long phone;

    private String firstName;

    private String lastName;

    private String password;
}
