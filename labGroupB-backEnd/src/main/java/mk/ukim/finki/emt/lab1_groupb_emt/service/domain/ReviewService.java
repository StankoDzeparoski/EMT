package mk.ukim.finki.emt.lab1_groupb_emt.service.domain;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Optional<Review> findById(Long id);

    List<Review> findAll();

    Review create(Review product);

    Optional<Review> update(Long id, Review product);

    Optional<Review> deleteById(Long id);
}
