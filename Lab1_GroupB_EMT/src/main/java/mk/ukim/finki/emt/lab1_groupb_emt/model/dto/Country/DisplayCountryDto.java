package mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Country;

import jakarta.validation.constraints.NotBlank;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Country;

import java.util.List;

//private String name;
//    private String continent;
public record DisplayCountryDto (
        Long id,
        @NotBlank
        String name,
        String continent
) {
    public static mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Country.DisplayCountryDto from(Country country) {
        return new mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Country.DisplayCountryDto(
                country.getId(),
                country.getName(),
                country.getContinent()
        );
    }

    public static List<mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Country.DisplayCountryDto> from(List<Country> countries) {
        return countries
                .stream()
                .map(mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Country.DisplayCountryDto::from)
                .toList();
    }
}
