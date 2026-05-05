package mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Accomodation;

import jakarta.validation.constraints.NotBlank;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Review;

public record CreateReviewDto (
        @NotBlank
        String comment,
        Double rating
) {
    public Review toReview(Review review) {
        return new Review(comment, rating);
    }
}