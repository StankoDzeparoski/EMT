package mk.ukim.finki.emt.lab1_groupb_emt.service.application;

import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.CreateAccomodationDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.DisplayAccomodationDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.OptimizedAccommodationDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Category;
import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.AccommodationExtendedProjection;
import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.AccommodationProjection;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    Optional<DisplayAccomodationDto> findById(Long id);

    List<DisplayAccomodationDto> findAll();

    DisplayAccomodationDto create(CreateAccomodationDto createAccomodationDto);

    Optional<DisplayAccomodationDto> update(Long id, CreateAccomodationDto createAccomodationDto);

    Optional<DisplayAccomodationDto> deleteById(Long id);

    void addReview(Long accommodationId, String comment, Double rating);

    Page<DisplayAccomodationDto> findAll(int page, int size, String sortBy);

    Page<DisplayAccomodationDto> findAllWithFilters(
            int page,
            int size,
            String sortBy,
            Category category,
            Long hostId,
            Long countryId,
            Integer numRooms,
            Boolean hasAvailableRooms);

    Page<AccommodationExtendedProjection> findAllExtendedProjection(int page, int size);

    Page<AccommodationProjection> findAllProjection(int page, int size);

    List<OptimizedAccommodationDto> findAllWithHostAndCountry();

    List<OptimizedAccommodationDto> findAllWithHost();
}
