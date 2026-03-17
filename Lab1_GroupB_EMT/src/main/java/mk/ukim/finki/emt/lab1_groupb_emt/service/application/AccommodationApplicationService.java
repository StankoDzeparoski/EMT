package mk.ukim.finki.emt.lab1_groupb_emt.service.application;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Review;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.CreateAccomodationDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.DisplayAccomodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    Optional<DisplayAccomodationDto> findById(Long id);

    List<DisplayAccomodationDto> findAll();

    DisplayAccomodationDto create(CreateAccomodationDto createAccomodationDto);

    Optional<DisplayAccomodationDto> update(Long id, CreateAccomodationDto createAccomodationDto);

    Optional<DisplayAccomodationDto> deleteById(Long id);

    void addReview(Long accommodationId, String comment, Double rating);
}
