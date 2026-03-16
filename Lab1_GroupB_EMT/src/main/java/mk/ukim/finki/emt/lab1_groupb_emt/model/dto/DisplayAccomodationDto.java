package mk.ukim.finki.emt.lab1_groupb_emt.model.dto;

import jakarta.validation.constraints.NotBlank;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Accomodation;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Host;
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
    Boolean occupied
) {
    public static DisplayAccomodationDto from(Accomodation accomodation) {
        return new DisplayAccomodationDto(
                accomodation.getId(),
                accomodation.getName(),
                accomodation.getCategory(),
                accomodation.getCondition(),
                accomodation.getHost(),
                accomodation.getNumRooms(),
                accomodation.getOccupied()
        );
    }

    public static List<DisplayAccomodationDto> from(List<Accomodation> accomodations) {
        return accomodations
                .stream()
                .map(DisplayAccomodationDto::from)
                .toList();
    }
}
