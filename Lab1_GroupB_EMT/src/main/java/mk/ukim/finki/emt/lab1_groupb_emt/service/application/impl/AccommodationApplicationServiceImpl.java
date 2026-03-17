package mk.ukim.finki.emt.lab1_groupb_emt.service.application.impl;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Host;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.CreateAccomodationDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.DisplayAccomodationDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.exception.HostNotFoundException;
import mk.ukim.finki.emt.lab1_groupb_emt.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.lab1_groupb_emt.service.domain.AccommodationService;
import mk.ukim.finki.emt.lab1_groupb_emt.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public Optional<DisplayAccomodationDto> findById(Long id) {
        return accommodationService.findById(id)
                .map(DisplayAccomodationDto::from);
    }

    @Override
    public List<DisplayAccomodationDto> findAll() {
        return DisplayAccomodationDto.from(accommodationService.findAll());
    }

    @Override
    public DisplayAccomodationDto create(CreateAccomodationDto createAccomodationDto) {
        Host host = hostService
                .findById(createAccomodationDto.hostId())
                .orElseThrow(() -> new HostNotFoundException(createAccomodationDto.hostId()));
        return DisplayAccomodationDto.from(accommodationService.create(createAccomodationDto.toAccomodation(host)));
    }

    @Override
    public Optional<DisplayAccomodationDto> update(Long id, CreateAccomodationDto createAccomodationDto) {
        Host host = hostService
                .findById(createAccomodationDto.hostId())
                .orElseThrow(() -> new HostNotFoundException(createAccomodationDto.hostId()));
        return accommodationService
                .update(id, createAccomodationDto.toAccomodation(host))
                .map(DisplayAccomodationDto::from);
    }

    @Override
    public Optional<DisplayAccomodationDto> deleteById(Long id) {
        return accommodationService
                .deleteById(id)
                .map(DisplayAccomodationDto::from);
    }

    @Override
    public void addReview(Long accommodationId, String comment, Double rating) {
        accommodationService.addReview(accommodationId, comment, rating);
    }
}