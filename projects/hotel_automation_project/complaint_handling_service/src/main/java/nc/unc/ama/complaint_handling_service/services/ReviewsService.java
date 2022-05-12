package nc.unc.ama.complaint_handling_service.services;

import nc.unc.ama.common.dto.LogEntryDTO;
import nc.unc.ama.common.dto.LogsREST;
import nc.unc.ama.complaint_handling_service.entities.Reviews;
import nc.unc.ama.complaint_handling_service.repositories.ReviewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ReviewsService {
    private final ReviewsRepo reviewsRepo;

    private final LogsREST logsApi;

    @Autowired
    public ReviewsService(ReviewsRepo reviewsRepo, final LogsREST logsApi) {
        this.reviewsRepo = reviewsRepo;
        this.logsApi = logsApi;
    }

    @Transactional
    public Reviews createReview(Reviews review) {
        this.logsApi.addLog(new LogEntryDTO(new Date(), "reviewsService", "created review"));
        return reviewsRepo.save(review);
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
