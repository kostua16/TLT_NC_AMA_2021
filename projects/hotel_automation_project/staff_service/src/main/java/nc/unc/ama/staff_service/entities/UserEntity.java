package nc.unc.ama.staff_service.entities;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nc.unc.ama.common.dto.UserInfoDTO;
import nc.unc.ama.common.dto.UserRegistrationDTO;
import nc.unc.ama.common.dto.UserRoles;
import nc.unc.ama.common.dto.UserStatus;
import nc.unc.ama.common.dto.UserUpdateDTO;
import org.hibernate.annotations.NaturalId;

@Setter
@Getter
@Entity
@AllArgsConstructor
@Table(name = "USERS")
public class UserEntity {

    @Id
    private UUID uuid;

    @NaturalId(mutable = true)
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private long phone;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String password;

    @Column(nullable = false)
    private int rating;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UserRoles role;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UserStatus status;

    public UserEntity() {
        this.uuid = UUID.randomUUID();
        this.rating = 0;
    }

    public UserInfoDTO toInfo() {
        return new UserInfoDTO(
            this.getUuid(),
            this.getEmail(),
            this.getPhone(),
            this.getFirstName(),
            this.getLastName(),
            this.getPassword(),
            this.getRating(),
            this.getRole(),
            this.getStatus()
        );
    }

    public UserEntity update(final UserUpdateDTO upd, final String password) {
        this.setEmail(upd.getEmail());
        this.setPhone(upd.getPhone());
        this.setFirstName(upd.getFirstName());
        this.setLastName(upd.getLastName());
        this.setPassword(password);
        return this;
    }

    public static UserEntity newUser(
        final UserRegistrationDTO reg,
        final UserRoles role,
        final String password
    ) {
        final UserEntity res = new UserEntity();
        res.setEmail(reg.getEmail());
        res.setPhone(reg.getPhone());
        res.setFirstName(reg.getFirstName());
        res.setLastName(reg.getLastName());
        res.setPassword(password);
        res.setStatus(UserStatus.LOCKED);
        res.setRole(role);
        return res;
    }
}
