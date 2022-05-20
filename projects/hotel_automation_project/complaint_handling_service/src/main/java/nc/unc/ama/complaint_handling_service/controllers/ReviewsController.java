package nc.unc.ama.complaint_handling_service.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import nc.unc.ama.common.dto.ReviewCreateDTO;
import nc.unc.ama.common.dto.ReviewsDTO;
import nc.unc.ama.complaint_handling_service.entities.Reviews;
import nc.unc.ama.complaint_handling_service.services.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/reviews")
public class ReviewsController {

    private final ReviewsService reviewsService;

    @Autowired
    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<ReviewsDTO> createReview(@RequestBody ReviewCreateDTO reviewsDTO) {
        final Reviews reviews = reviewsService.createReview(Reviews
            .builder()
            .guestId(reviewsDTO.getGuestId())
            .reviewText(reviewsDTO.getReviewText())
            .build()
        );
        return ResponseEntity.ok(new ReviewsDTO(
            reviews.getReviewId(),
            reviews.getGuestId(),
            reviews.getReviewText()
        ));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ReviewsDTO> viewReview(@PathVariable("id") Long reviewId) {
        Reviews newReview = reviewsService.getReview(reviewId);
        return ResponseEntity.ok(new ReviewsDTO(
            newReview.getReviewId(),
            newReview.getGuestId(),
            newReview.getReviewText()
        ));
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<ReviewsDTO>> getAllReviews() {
        List<ReviewsDTO> reviewsDTOList = new ArrayList<>();
        for (Reviews reviews : reviewsService.getAllReviews()) {
            reviewsDTOList.add(new ReviewsDTO(
                reviews.getReviewId(),
                reviews.getGuestId(),
                reviews.getReviewText()
            ));
        }
        return ResponseEntity.ok(reviewsDTOList);
    }

    @GetMapping(path = "/on-guest/{id}")
    public ResponseEntity<List<ReviewsDTO>> getReviewsOnGuest(@PathVariable("id") UUID guest) {
        List<ReviewsDTO> reviewsDTOList = new ArrayList<>();
        for (Reviews reviews :
            reviewsService.getReviewByGuestId(guest)) {
            reviewsDTOList.add(new ReviewsDTO(
                reviews.getReviewId(),
                reviews.getGuestId(),
                reviews.getReviewText()
            ));
        }
        return ResponseEntity.ok(reviewsDTOList);
    }
}
