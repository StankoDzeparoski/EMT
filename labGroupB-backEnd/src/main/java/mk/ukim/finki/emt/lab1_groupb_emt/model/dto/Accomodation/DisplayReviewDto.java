package mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Accomodation;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Review;

import java.util.List;


public record DisplayReviewDto(
        String comment,
        Double rating
) {
    public static DisplayReviewDto from(Review review) {
        return new DisplayReviewDto(
                review.getComment(),
                review.getRating()
        );
    }

    public static List<DisplayReviewDto> from(List<Review> reviews) {
        return reviews
                .stream()
                .map(DisplayReviewDto::from)
                .toList();
    }

}