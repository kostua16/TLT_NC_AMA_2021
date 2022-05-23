package nc.unc.ama.common.dto;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@SuppressWarnings("PMD.ShortVariable")
public class UserDTO implements Serializable {

    private UUID id;

    private String email;

    private long phone;

    private String firstName;

    private String lastName;

    private int rating;

    private Long staffTypeId;

    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
}
