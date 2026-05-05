package mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Host;

import jakarta.validation.constraints.NotBlank;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Country;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Host;

import java.util.List;

public record DisplayHostDto (
        Long id,
        @NotBlank
        String name,
        String surname,
        Country country
) {
    public static mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Host.DisplayHostDto from(Host host) {
        return new mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Host.DisplayHostDto(
                host.getId(),
                host.getName(),
                host.getSurname(),
                host.getCountry()
        );
    }

    public static List<mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Host.DisplayHostDto> from(List<Host> hosts) {
        return hosts
                .stream()
                .map(mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Host.DisplayHostDto::from)
                .toList();
    }
}