package nc.unc.ama.common.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewsDTO {

        private Long reviewId;
        private Long guestId;
        private String reviewText;

        public ReviewsDTO(Long reviewId, Long guestId, String reviewText) {
            this.reviewId = reviewId;
            this.guestId = guestId;
            this.reviewText = reviewText;
        }

}
