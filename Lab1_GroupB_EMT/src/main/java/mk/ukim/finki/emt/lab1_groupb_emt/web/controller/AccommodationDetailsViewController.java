package mk.ukim.finki.emt.lab1_groupb_emt.web.controller;

import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.AccommodationDetailsViewProjection;
import mk.ukim.finki.emt.lab1_groupb_emt.service.application.AccommodationApplicationDetailsViewService;
import mk.ukim.finki.emt.lab1_groupb_emt.service.application.AccommodationStatisticsByCategoryService;
import mk.ukim.finki.emt.lab1_groupb_emt.web.dto.AccommodationStatisticsByCategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for accessing accommodation details view.
 * Provides read-only endpoints for querying simplified accommodation information
 * from the database view.
 */
@RestController
@RequestMapping("/api/accommodations-view")
public class AccommodationDetailsViewController {

    private final AccommodationApplicationDetailsViewService service;
    private final AccommodationStatisticsByCategoryService statisticsService;

    public AccommodationDetailsViewController(
            AccommodationApplicationDetailsViewService service,
            AccommodationStatisticsByCategoryService statisticsService) {
        this.service = service;
        this.statisticsService = statisticsService;
    }


    /**
     * Get all accommodations from the view with pagination.
     *
     * @param page page number (0-based), default: 0
     * @param size page size, default: 10
     * @return paginated accommodation details
     */
    @GetMapping
    public ResponseEntity<Page<AccommodationDetailsViewProjection>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    /**
     * Get all accommodation statistics grouped by category from the materialized view.
     *
     * @return list of accommodation statistics by category
     */
    @GetMapping("/statistics")
    public ResponseEntity<List<AccommodationStatisticsByCategoryDto>> getAllStatistics() {
        return ResponseEntity.ok(statisticsService.getAllStatistics());
    }

    /**
     * Get statistics for a specific accommodation category.
     *
     * @param category the accommodation category (ROOM, HOUSE, FLAT, APARTMENT, HOTEL, MOTEL)
     * @return statistics for the specified category
     */
    @GetMapping("/statistics/{category}")
    public ResponseEntity<AccommodationStatisticsByCategoryDto> getStatisticsByCategory(
            @PathVariable String category) {
        return statisticsService.getStatisticsByCategory(category)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get statistics for categories with at least a minimum number of accommodations.
     *
     * @param minAccommodations minimum number of accommodations in a category
     * @return list of statistics for categories meeting the criteria
     */
    @GetMapping("/statistics/filter/by-min-accommodations")
    public ResponseEntity<List<AccommodationStatisticsByCategoryDto>> getStatisticsByMinimumAccommodations(
            @RequestParam Long minAccommodations) {
        return ResponseEntity.ok(statisticsService.getStatisticsByMinimumAccommodations(minAccommodations));
    }

}

