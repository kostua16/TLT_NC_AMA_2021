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
public class UserInfoDTO implements Serializable {

    private UUID id;

    private String email;

    private long phone;

    private String firstName;

    private String lastName;

    private String password;

    private int rating;

    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public UserDTO toSummary() {
        return new UserDTO(
            this.id,
            this.email,
            this.phone,
            this.firstName,
            this.lastName,
            this.rating,
            this.role,
            this.status
        );
    }
}
