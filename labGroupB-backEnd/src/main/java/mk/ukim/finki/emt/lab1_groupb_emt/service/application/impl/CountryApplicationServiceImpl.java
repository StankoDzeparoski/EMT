package mk.ukim.finki.emt.lab1_groupb_emt.service.application.impl;

import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Country.CreateCountryDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Country.DisplayCountryDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Host.DisplayHostDto;
import mk.ukim.finki.emt.lab1_groupb_emt.service.application.CountryApplicationService;
import mk.ukim.finki.emt.lab1_groupb_emt.service.domain.CountryService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id)
                .map(DisplayCountryDto::from);
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return DisplayCountryDto.from(countryService.findAll());
    }

    @Override
    public DisplayCountryDto create(CreateCountryDto createCountryDto) {
        return DisplayCountryDto.from(
                countryService.create(createCountryDto.toCountry(createCountryDto.name(), createCountryDto.continent())));
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto createCountryDto) {
        return countryService
                .update(id, createCountryDto.toCountry(createCountryDto.name(), createCountryDto.continent()))
                .map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> deleteById(Long id) {
        return countryService
                .deleteById(id)
                .map(DisplayCountryDto::from);
    }

    @Override
    public Page<DisplayCountryDto> findAll(int page, int size, String sortBy) {
        return countryService.findAll(page, size, sortBy)
                .map(DisplayCountryDto::from);
    }
}
