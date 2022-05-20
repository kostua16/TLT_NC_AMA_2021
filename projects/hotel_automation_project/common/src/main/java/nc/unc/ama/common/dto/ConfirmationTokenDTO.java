package nc.unc.ama.common.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConfirmationTokenDTO {

    private String token;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    private UserInfoDTO user;
}
