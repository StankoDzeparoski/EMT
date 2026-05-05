package mk.ukim.finki.emt.lab1_groupb_emt.web.controller;

import jakarta.validation.Valid;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Accomodation.CreateAccomodationDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Accomodation.DisplayAccomodationDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Accomodation.OptimizedAccommodationDto;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Category;
import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.Accommodation.AccommodationExtendedProjection;
import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.Accommodation.AccommodationProjection;
import mk.ukim.finki.emt.lab1_groupb_emt.repository.AccomodationRepository;
import mk.ukim.finki.emt.lab1_groupb_emt.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.lab1_groupb_emt.service.domain.AccommodationService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationApplicationService accommodationApplicationService;
    private final AccommodationService accommodationService;
    private final AccomodationRepository accomodationRepository;

    public AccommodationController(
            AccommodationApplicationService accommodationApplicationService,
            AccommodationService accommodationService,
            AccomodationRepository accomodationRepository) {
        this.accommodationApplicationService = accommodationApplicationService;
        this.accommodationService = accommodationService;
        this.accomodationRepository = accomodationRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccomodationDto> findById(@PathVariable Long id) {
        return accommodationApplicationService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/add-review")
    public ResponseEntity<Void> addReview(
            @PathVariable Long id, @RequestParam String comment, @RequestParam Double rating) {
        accommodationApplicationService
                .addReview(id, comment, rating);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DisplayAccomodationDto>> findAll() {
        return ResponseEntity.ok(accommodationApplicationService.findAll());
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<DisplayAccomodationDto>> findAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy
    ) {
        return ResponseEntity.ok(accommodationApplicationService.findAll(page, size, sortBy));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<DisplayAccomodationDto>> findAllWithFilters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Long hostId,
            @RequestParam(required = false) Long countryId,
            @RequestParam(required = false) Integer numRooms,
            @RequestParam(required = false) Boolean hasAvailableRooms
    ) {
        return ResponseEntity.ok(accommodationApplicationService.findAllWithFilters(
                page, size, sortBy, category, hostId, countryId, numRooms, hasAvailableRooms
        ));
    }

    @GetMapping("/projection")
    public ResponseEntity<Page<AccommodationProjection>> findAllProjection(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(accommodationApplicationService.findAllProjection(page, size));
    }

    @GetMapping("/projectionExtended")
    public ResponseEntity<Page<AccommodationExtendedProjection>> findAllExtendedProjection(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(accommodationApplicationService.findAllExtendedProjection(page, size));
    }

    // ========== EntityGraph Optimized Endpoints ==========

    /**
     * Endpoint 1: Get all accommodations with host loaded.
     * Returns OptimizedAccommodationDto with accommodation and host information.
     * Avoids N+1 query problem - single query with JOIN to hosts table.
     */
    @GetMapping("/optimized/with-host")
    public ResponseEntity<List<OptimizedAccommodationDto>> findAllWithHost() {
        return ResponseEntity.ok(accommodationApplicationService.findAllWithHost());
    }

    /**
     * Endpoint 2: Get all accommodations with host and country loaded.
     * Returns OptimizedAccommodationDto with accommodation, host, and country information.
     * Avoids N+1 query problem - single query with JOINs to hosts and countries tables.
     */
    @GetMapping("/optimized/with-host-and-country")
    public ResponseEntity<List<OptimizedAccommodationDto>> findAllWithHostAndCountry() {
        return ResponseEntity.ok(accommodationApplicationService.findAllWithHostAndCountry());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayAccomodationDto> create(@RequestBody @Valid CreateAccomodationDto createProductDto) {
        return ResponseEntity.ok(accommodationApplicationService.create(createProductDto));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DisplayAccomodationDto> update(
            @PathVariable Long id,
            @RequestBody CreateAccomodationDto createProductDto
    ) {
        return accommodationApplicationService
                .update(id, createProductDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayAccomodationDto> deleteById(@PathVariable Long id) {
        return accommodationApplicationService
                .deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    /**
     * Endpoint for renting an accommodation.
     * Marks the accommodation as occupied and publishes an AccommodationRentedEvent.
     * The event triggers multiple handlers:
     * - Logging the rental details
     * - Recording activity in the database
     * - Special handling if accommodation becomes fully occupied
     *
     * @param id the accommodation ID to rent
     * @return the updated accommodation or 404 if not found
     */
    @PostMapping("/{id}/rent")
    public ResponseEntity<DisplayAccomodationDto> rentAccommodation(@PathVariable Long id) {
        try {
            accommodationService.rentAccommodation(id);
            return accommodationApplicationService
                    .findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}

