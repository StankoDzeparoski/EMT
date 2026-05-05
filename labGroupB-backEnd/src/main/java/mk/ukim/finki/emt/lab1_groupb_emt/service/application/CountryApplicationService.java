package mk.ukim.finki.emt.lab1_groupb_emt.service.application;

import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Country.CreateCountryDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Country.DisplayCountryDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    Optional<DisplayCountryDto> findById(Long id);

    List<DisplayCountryDto> findAll();

    DisplayCountryDto create(CreateCountryDto createCountryDto);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto createCountryDto);

    Optional<DisplayCountryDto> deleteById(Long id);

    Page<DisplayCountryDto> findAll(int page, int size, String sortBy);

}
