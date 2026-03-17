package mk.ukim.finki.emt.lab1_groupb_emt.service.domain.impl;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Review;
import mk.ukim.finki.emt.lab1_groupb_emt.repository.ReviewRepository;
import mk.ukim.finki.emt.lab1_groupb_emt.service.domain.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review create(Review product) {
        return reviewRepository.save(product);
    }

    @Override
    public Optional<Review> update(Long id, Review product) {
        return reviewRepository.findById(id)
                .map((existingReview) -> {
                    existingReview.setComment(product.getComment());
                    existingReview.setRating(product.getRating());
                    existingReview.setAccomodation(product.getAccomodation());
                    return reviewRepository.save(existingReview);
                });
    }

    @Override
    public Optional<Review> deleteById(Long id) {
            Optional<Review> review = reviewRepository.findById(id);
            review.ifPresent(reviewRepository::delete);
            return review;
    }
}
