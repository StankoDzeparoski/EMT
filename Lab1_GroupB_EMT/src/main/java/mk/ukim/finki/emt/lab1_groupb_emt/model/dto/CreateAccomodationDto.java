package mk.ukim.finki.emt.lab1_groupb_emt.model.dto;


import jakarta.validation.constraints.NotBlank;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Accomodation;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Host;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Category;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Condition;

public record CreateAccomodationDto (
        @NotBlank
        String name,
        Category category,
        Condition condition,
        Long hostId,
        Integer numRooms,
        Boolean occupied
) {
    public Accomodation toAccomodation(Host host) {
        return new Accomodation(name, category, condition, host, numRooms, occupied);
    }
}
