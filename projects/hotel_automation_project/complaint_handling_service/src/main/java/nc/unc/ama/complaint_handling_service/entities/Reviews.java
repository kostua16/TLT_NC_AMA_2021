package nc.unc.ama.complaint_handling_service.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    private Long guestId;
    private String reviewText;

    @Builder
    public Reviews(Long reviewId, Long guestId, String reviewText) {
        this.reviewId = reviewId;
        this.guestId = guestId;
        this.reviewText = reviewText;
    }

    public Reviews() {

    }
}
