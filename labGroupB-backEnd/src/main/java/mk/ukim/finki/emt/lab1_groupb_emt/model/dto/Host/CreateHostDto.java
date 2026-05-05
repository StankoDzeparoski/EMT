package mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Host;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Accomodation;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Country;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Host;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Category;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Condition;

public record CreateHostDto (
        @NotBlank
        String name,
        String surname,
        Long countryId
) {
    public Host toHost(Country country) {
        return new Host(name, surname, country);
    }
}
