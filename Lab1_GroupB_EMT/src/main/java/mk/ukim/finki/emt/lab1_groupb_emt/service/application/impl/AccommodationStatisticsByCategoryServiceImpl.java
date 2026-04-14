package mk.ukim.finki.emt.lab1_groupb_emt.service.application.impl;

import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.AccommodationStatisticsByCategoryProjection;
import mk.ukim.finki.emt.lab1_groupb_emt.repository.AccommodationStatisticsByCategoryRepository;
import mk.ukim.finki.emt.lab1_groupb_emt.service.application.AccommodationStatisticsByCategoryService;
import mk.ukim.finki.emt.lab1_groupb_emt.web.dto.AccommodationStatisticsByCategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of AccommodationStatisticsByCategoryService.
 * Provides business logic for accessing and querying accommodation statistics by category.
 */
@Service
public class AccommodationStatisticsByCategoryServiceImpl implements AccommodationStatisticsByCategoryService {
    
    private final AccommodationStatisticsByCategoryRepository repository;
    
    public AccommodationStatisticsByCategoryServiceImpl(AccommodationStatisticsByCategoryRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public List<AccommodationStatisticsByCategoryDto> getAllStatistics() {
        return repository.findAllStatistics()
                .stream()
                .map(this::projectionToDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<AccommodationStatisticsByCategoryDto> getStatisticsByCategory(String category) {
        return repository.findByCategory(category)
                .map(this::projectionToDto);
    }
    
    @Override
    public List<AccommodationStatisticsByCategoryDto> getStatisticsByMinimumAccommodations(Long minAccommodations) {
        return repository.findByMinimumAccommodations(minAccommodations)
                .stream()
                .map(this::projectionToDto)
                .collect(Collectors.toList());
    }
    
    /**
     * Convert a projection to DTO.
     *
     * @param projection the projection to convert
     * @return the converted DTO
     */
    private AccommodationStatisticsByCategoryDto projectionToDto(AccommodationStatisticsByCategoryProjection projection) {
        return new AccommodationStatisticsByCategoryDto(
                projection.getCategory(),
                projection.getTotalAccommodations(),
                projection.getTotalRooms(),
                projection.getAvgRoomsPerAccommodation()
        );
    }
}

