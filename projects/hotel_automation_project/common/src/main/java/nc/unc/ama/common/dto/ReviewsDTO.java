package nc.unc.ama.common.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class ReviewsDTO extends ReviewCreateDTO{

        private Long reviewId;

    public ReviewsDTO(Long reviewId, Long guestId, String reviewText) {
        super(guestId, reviewText);
        this.reviewId = reviewId;
    }
}
