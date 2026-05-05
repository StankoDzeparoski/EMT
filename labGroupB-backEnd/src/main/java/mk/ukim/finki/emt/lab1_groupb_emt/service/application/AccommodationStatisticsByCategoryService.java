package mk.ukim.finki.emt.lab1_groupb_emt.service.application;

import mk.ukim.finki.emt.lab1_groupb_emt.web.dto.AccommodationStatisticsByCategoryDto;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for accessing accommodation statistics by category.
 */
public interface AccommodationStatisticsByCategoryService {
    
    /**
     * Get all accommodation statistics grouped by category.
     *
     * @return list of accommodation statistics
     */
    List<AccommodationStatisticsByCategoryDto> getAllStatistics();
    
    /**
     * Get statistics for a specific category.
     *
     * @param category the accommodation category
     * @return statistics for the specified category
     */
    Optional<AccommodationStatisticsByCategoryDto> getStatisticsByCategory(String category);
    
    /**
     * Get statistics for categories with at least a minimum number of accommodations.
     *
     * @param minAccommodations minimum number of accommodations
     * @return list of statistics meeting the criteria
     */
    List<AccommodationStatisticsByCategoryDto> getStatisticsByMinimumAccommodations(Long minAccommodations);
}

