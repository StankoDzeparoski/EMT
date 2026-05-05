package mk.ukim.finki.emt.lab1_groupb_emt.service.application.impl;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Country;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Host;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Accomodation.DisplayAccomodationDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Host.CreateHostDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Host.DisplayHostDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.exception.CountryNotFoundException;
import mk.ukim.finki.emt.lab1_groupb_emt.model.exception.HostNotFoundException;
import mk.ukim.finki.emt.lab1_groupb_emt.service.application.HostApplicationService;
import mk.ukim.finki.emt.lab1_groupb_emt.service.domain.CountryService;
import mk.ukim.finki.emt.lab1_groupb_emt.service.domain.HostService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }


    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id)
                .map(DisplayHostDto::from);
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return DisplayHostDto.from(hostService.findAll());
    }

    @Override
    public DisplayHostDto create(CreateHostDto createHostDto) {
        Country country = countryService
                .findById(createHostDto.countryId())
                .orElseThrow(() -> new CountryNotFoundException(createHostDto.countryId()));
        return DisplayHostDto.from(hostService.create(createHostDto.toHost(country)));
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto) {
        Country country = countryService
                .findById(createHostDto.countryId())
                .orElseThrow(() -> new CountryNotFoundException(createHostDto.countryId()));
        return hostService
                .update(id, createHostDto.toHost(country))
                .map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> deleteById(Long id) {
        return hostService
                .deleteById(id)
                .map(DisplayHostDto::from);
    }

    @Override
    public Page<DisplayHostDto> findAll(int page, int size, String sortBy) {
        return hostService.findAll(page, size, sortBy)
                .map(DisplayHostDto::from);
    }
}
