package mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Accomodation;

import jakarta.validation.constraints.NotBlank;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Accomodation;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Host;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Review;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Category;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Condition;

import java.util.List;

public record DisplayAccomodationDto (
        Long id,
        @NotBlank
        String name,
        Category category,
        Condition condition,
        Host host,
        Integer numRooms,
        Boolean occupied,
        Double averageRating,
        List<DisplayReviewDto> reviews

) {
    public static DisplayAccomodationDto from(Accomodation accomodation) {
        return new DisplayAccomodationDto(
                accomodation.getId(),
                accomodation.getName(),
                accomodation.getCategory(),
                accomodation.getCondition(),
                accomodation.getHost(),
                accomodation.getNumRooms(),
                accomodation.getOccupied(),
                accomodation.getReviews().stream().mapToDouble(Review::getRating).average().orElse(0.0),
                accomodation.getReviews().stream().map(DisplayReviewDto::from).toList()
        );
    }

    public static List<DisplayAccomodationDto> from(List<Accomodation> accomodations) {
        return accomodations
                .stream()
                .map(DisplayAccomodationDto::from)
                .toList();
    }
}
