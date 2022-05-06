package nc.unc.ama.complaint_handling_service.controllers;

import nc.unc.ama.common.dto.GuestDTO;
import nc.unc.ama.common.dto.ReviewsDTO;
import nc.unc.ama.complaint_handling_service.entities.Reviews;
import nc.unc.ama.complaint_handling_service.services.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/reviews")
public class ReviewsController {
    private final ReviewsService reviewsService;

    @Autowired
    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @PostMapping(path = "/")
    public void createReview(@RequestBody ReviewsDTO reviewsDTO) {
        reviewsService.createReview(Reviews
            .builder()
            .reviewId(reviewsDTO.getReviewId())
            .guestId(reviewsDTO.getGuestId())
            .reviewText(reviewsDTO.getReviewText())
            .build()
        );
    }

    @GetMapping(path = "/{reviewId}")
    public ReviewsDTO viewReview(@PathVariable("reviewId") Long reviewId) {
        Reviews newReview = reviewsService.getReview(reviewId);
        return new ReviewsDTO(
            newReview.getReviewId(),
            newReview.getGuestId(),
            newReview.getReviewText()
        );
    }

    @GetMapping(path = "/")
    public List<ReviewsDTO> getAllReviews() {
        List<ReviewsDTO> reviewsDTOList = new ArrayList<>();
        for (Reviews reviews : reviewsService.getAllReviews()) {
            reviewsDTOList.add(new ReviewsDTO(
                reviews.getReviewId(),
                reviews.getGuestId(),
                reviews.getReviewText()
            ));
        }
        return reviewsDTOList;
    }

    @GetMapping(path = "/on-guest")
    public List<ReviewsDTO> getReviewsOnGuest(GuestDTO guestDTO) {
        List<ReviewsDTO> reviewsDTOList = new ArrayList<>();
        for (Reviews reviews :
            reviewsService.getReviewByGuestId(guestDTO.getGuestId())) {
            reviewsDTOList.add(new ReviewsDTO(
                reviews.getReviewId(),
                reviews.getGuestId(),
                reviews.getReviewText()
            ));
        }
        return reviewsDTOList;
    }
}
