package nc.unc.ama.common.dto;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class ReviewsDTO extends ReviewCreateDTO{

        private UUID reviewId;

    public ReviewsDTO(Long reviewId, UUID guestId, String reviewText) {
        super(guestId, reviewText);
        this.reviewId = reviewId;
    }
}
