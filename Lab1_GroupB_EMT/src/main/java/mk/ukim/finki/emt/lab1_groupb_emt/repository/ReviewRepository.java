package mk.ukim.finki.emt.lab1_groupb_emt.repository;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
