package nc.unc.ama.staff_service.entities;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import nc.unc.ama.common.dto.ConfirmationTokenDTO;

@Getter
@Setter
@Entity
@Table(name = "CONFIRM_TOKENS")
public class ConfirmationToken {

    @Id
    private UUID tokenId;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity user;

    public ConfirmationToken() {
        this.tokenId = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.expiresAt = LocalDateTime.now().plusMinutes(30);
        this.token = UUID.randomUUID().toString();
    }

    public ConfirmationToken(UserEntity user) {
        this();
        this.user = user;
    }

    public ConfirmationTokenDTO toInfo() {
        return new ConfirmationTokenDTO(
            this.token,
            this.createdAt,
            this.expiresAt,
            this.user.toInfo()
        );
    }
}
