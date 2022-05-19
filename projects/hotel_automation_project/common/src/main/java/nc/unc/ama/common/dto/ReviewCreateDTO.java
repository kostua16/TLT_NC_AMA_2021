package nc.unc.ama.common.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewCreateDTO {
    private UUID guestId;
    private String reviewText;
}
