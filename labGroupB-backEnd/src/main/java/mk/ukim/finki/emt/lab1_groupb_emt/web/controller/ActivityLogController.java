package mk.ukim.finki.emt.lab1_groupb_emt.web.controller;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.AccommodationActivityLog;
import mk.ukim.finki.emt.lab1_groupb_emt.repository.AccommodationActivityLogRepository;
import mk.ukim.finki.emt.lab1_groupb_emt.web.dto.PopularAccommodationDto;
import mk.ukim.finki.emt.lab1_groupb_emt.web.dto.PopularHostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * REST Controller for managing accommodation activity logs.
 * Provides endpoints for viewing and filtering accommodation activities such as rentals and occupancy events.
 */
@RestController
@RequestMapping("/api/activities")
public class ActivityLogController {
    
    private final AccommodationActivityLogRepository activityLogRepository;

    public ActivityLogController(AccommodationActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    /**
     * Get all activity logs with pagination.
     *
     * @param page page number (0-based), default: 0
     * @param size page size, default: 20
     * @param sortBy sort field, default: createdAt (descending)
     * @return paginated activity logs
     */
    @GetMapping
    public ResponseEntity<Page<AccommodationActivityLog>> getAllActivities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        Page<AccommodationActivityLog> activities = activityLogRepository.findAll(pageable);
        
        return ResponseEntity.ok(activities);
    }

    /**
     * Get activity logs for a specific accommodation.
     *
     * @param accommodationId the accommodation ID
     * @param page page number (0-based)
     * @param size page size
     * @return paginated activities for the accommodation
     */
    @GetMapping("/accommodation/{accommodationId}")
    public ResponseEntity<Page<AccommodationActivityLog>> getActivitiesByAccommodation(
            @PathVariable Long accommodationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<AccommodationActivityLog> activities = activityLogRepository.findByAccommodationId(accommodationId, pageable);
        
        return ResponseEntity.ok(activities);
    }

    /**
     * Get activity logs filtered by activity type.
     *
     * @param activityType the type of activity (RENTED, FULLY_OCCUPIED, etc.)
     * @param page page number (0-based)
     * @param size page size
     * @return paginated activities matching the type
     */
    @GetMapping("/type/{activityType}")
    public ResponseEntity<Page<AccommodationActivityLog>> getActivitiesByType(
            @PathVariable String activityType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<AccommodationActivityLog> activities = activityLogRepository.findByActivityType(activityType, pageable);
        
        return ResponseEntity.ok(activities);
    }

    /**
     * Get recently rented accommodations.
     *
     * @param page page number (0-based)
     * @param size page size
     * @return paginated recently rented activities
     */
    @GetMapping("/rented")
    public ResponseEntity<Page<AccommodationActivityLog>> getRecentlyRented(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<AccommodationActivityLog> activities = activityLogRepository.findByActivityType("RENTED", pageable);
        
        return ResponseEntity.ok(activities);
    }

    /**
     * Get fully occupied accommodations.
     *
     * @param page page number (0-based)
     * @param size page size
     * @return paginated fully occupied activities
     */
    @GetMapping("/fully-occupied")
    public ResponseEntity<Page<AccommodationActivityLog>> getFullyOccupied(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<AccommodationActivityLog> activities = activityLogRepository.findByActivityType("FULLY_OCCUPIED", pageable);
        
        return ResponseEntity.ok(activities);
    }

    /**
     * Get total count of activities.
     *
     * @return total activity count
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getActivityCount() {
        long count = activityLogRepository.count();
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of activities for a specific accommodation.
     *
     * @param accommodationId the accommodation ID
     * @return activity count for the accommodation
     */
    @GetMapping("/accommodation/{accommodationId}/count")
    public ResponseEntity<Long> getActivityCountForAccommodation(
            @PathVariable Long accommodationId) {
        
        Pageable pageable = PageRequest.of(0, 1);
        long count = activityLogRepository.findByAccommodationId(accommodationId, pageable).getTotalElements();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get most popular accommodations sorted by rent count in descending order.
     * Shows top accommodations based on number of RENTED activities.
     *
     * @param page page number (0-based), default: 0
     * @param size page size, default: 10
     * @return list of most popular accommodations with rent statistics
     */
    @GetMapping("/popular/accommodations")
    public ResponseEntity<List<PopularAccommodationDto>> getMostPopularAccommodations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        List<Map<String, Object>> results = activityLogRepository.findMostPopularAccommodations("RENTED", pageable);
        
        List<PopularAccommodationDto> dtos = results.stream()
                .map(row -> new PopularAccommodationDto(
                        ((Number) row.get("accommodationId")).longValue(),
                        (String) row.get("accommodationName"),
                        (String) row.get("category"),
                        ((Number) row.get("rentCount")).longValue(),
                        (String) row.get("hostName"),
                        (String) row.get("hostSurname")
                ))
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(dtos);
    }

    /**
     * Get most popular hosts sorted by total rent count in descending order.
     * Shows top hosts based on combined rent count of their accommodations.
     *
     * @param page page number (0-based), default: 0
     * @param size page size, default: 10
     * @return list of most popular hosts with rent statistics
     */
    @GetMapping("/popular/hosts")
    public ResponseEntity<List<PopularHostDto>> getMostPopularHosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        List<Map<String, Object>> results = activityLogRepository.findMostPopularHosts("RENTED", pageable);
        
        List<PopularHostDto> dtos = results.stream()
                .map(row -> new PopularHostDto(
                        ((Number) row.get("hostId")).longValue(),
                        (String) row.get("hostName"),
                        (String) row.get("hostSurname"),
                        (String) row.get("email"),
                        (String) row.get("countryName"),
                        ((Number) row.get("totalRentCount")).longValue(),
                        ((Number) row.get("totalAccommodations")).longValue()
                ))
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(dtos);
    }
}


