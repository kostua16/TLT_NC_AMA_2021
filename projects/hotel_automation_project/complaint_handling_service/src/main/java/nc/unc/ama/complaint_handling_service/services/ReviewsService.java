package nc.unc.ama.complaint_handling_service.services;

import nc.unc.ama.complaint_handling_service.entities.Reviews;
import nc.unc.ama.complaint_handling_service.repositories.ReviewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewsService {
    private final ReviewsRepo reviewsRepo;

    @Autowired
    public ReviewsService(ReviewsRepo reviewsRepo) {
        this.reviewsRepo = reviewsRepo;
    }

    @Transactional
    public void createReview(Reviews review) {
        reviewsRepo.save(review);
    }

    public Reviews getReview(Long reviewId) {
        return reviewsRepo.findById(reviewId).get();
    }

    public List<Reviews> getAllReviews() {
        return reviewsRepo.findAll();
    }

    public List<Reviews> getReviewByGuestId(Long guestId) {
        return reviewsRepo.findReviewsByGuestId(guestId);
    }
}
