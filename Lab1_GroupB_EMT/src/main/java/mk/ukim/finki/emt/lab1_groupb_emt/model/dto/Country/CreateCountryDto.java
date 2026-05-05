package mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Country;

import jakarta.validation.constraints.NotBlank;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Country;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Host;

//private String name;
//    private String continent;
public record CreateCountryDto (
        @NotBlank
        String name,
        String continent
) {
    public Country toCountry(String name,
                             String continent) {
        return new Country(name, continent);
    }
}