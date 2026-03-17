package mk.ukim.finki.emt.lab1_groupb_emt.model.dto;

import jakarta.validation.constraints.NotBlank;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Accomodation;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Host;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Review;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Category;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Condition;

public record CreateReviewDto (
        @NotBlank
        String comment,
        Double rating
) {
    public Review toReview(Review review) {
        return new Review(comment, rating);
    }
}