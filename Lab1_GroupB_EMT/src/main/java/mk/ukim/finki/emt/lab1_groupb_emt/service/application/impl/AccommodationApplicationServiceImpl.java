package mk.ukim.finki.emt.lab1_groupb_emt.service.application.impl;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Host;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.CreateAccomodationDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.DisplayAccomodationDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.OptimizedAccommodationDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Category;
import mk.ukim.finki.emt.lab1_groupb_emt.model.exception.HostNotFoundException;
import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.AccommodationExtendedProjection;
import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.AccommodationProjection;
import mk.ukim.finki.emt.lab1_groupb_emt.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.lab1_groupb_emt.service.domain.AccommodationService;
import mk.ukim.finki.emt.lab1_groupb_emt.service.domain.HostService;
import org.springframework.data.domain.Page;
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

    @Override
    public Page<DisplayAccomodationDto> findAll(int page, int size, String sortBy) {
        return accommodationService.findAll(page, size, sortBy)
                .map(DisplayAccomodationDto::from);
    }

    @Override
    public Page<DisplayAccomodationDto> findAllWithFilters(
            int page,
            int size,
            String sortBy,
            Category category,
            Long hostId,
            Long countryId,
            Integer numRooms,
            Boolean hasAvailableRooms) {
        return accommodationService.findAllWithFilters(page, size, sortBy, category, hostId, countryId, numRooms, hasAvailableRooms)
                .map(DisplayAccomodationDto::from);
    }

    @Override
    public Page<AccommodationExtendedProjection> findAllExtendedProjection(int page, int size) {
        return accommodationService.findAllExtendedProjection(page,size);
    }

    @Override
    public Page<AccommodationProjection> findAllProjection(int page, int size) {
        return accommodationService.findAllProjection(page,size);
    }

    @Override
    public List<OptimizedAccommodationDto> findAllWithHostAndCountry() {
        return OptimizedAccommodationDto.from(accommodationService.findAllWithHostAndCountry());
    }

    @Override
    public List<OptimizedAccommodationDto> findAllWithHost() {
        return OptimizedAccommodationDto.from(accommodationService.findAllWithHost());
    }
}