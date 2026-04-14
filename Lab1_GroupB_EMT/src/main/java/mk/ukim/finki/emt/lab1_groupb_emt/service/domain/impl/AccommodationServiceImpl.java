package mk.ukim.finki.emt.lab1_groupb_emt.service.domain.impl;

import mk.ukim.finki.emt.lab1_groupb_emt.event.publisher.AccommodationEventPublisher;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Accomodation;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Review;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Category;
import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.AccommodationExtendedProjection;
import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.AccommodationProjection;
import mk.ukim.finki.emt.lab1_groupb_emt.repository.AccomodationRepository;
import mk.ukim.finki.emt.lab1_groupb_emt.repository.ReviewRepository;
import mk.ukim.finki.emt.lab1_groupb_emt.service.domain.AccommodationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccommodationServiceImpl implements AccommodationService {

    private final AccomodationRepository accomodationRepository;
    private final ReviewRepository reviewRepository;
    private final AccommodationEventPublisher eventPublisher;

    public AccommodationServiceImpl(
            AccomodationRepository accomodationRepository,
            ReviewRepository reviewRepository,
            AccommodationEventPublisher eventPublisher) {
        this.accomodationRepository = accomodationRepository;
        this.reviewRepository = reviewRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<Accomodation> findById(Long id) {
        return accomodationRepository.findById(id);
    }

    @Override
    public List<Accomodation> findAll() {
        return accomodationRepository.findAll();
    }

    // Pagination with sorting
    @Override
    public Page<Accomodation> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return accomodationRepository.findAll(pageable);
    }

    // Advanced filtering with pagination and sorting
    @Override
    public Page<Accomodation> findAllWithFilters(
            int page,
            int size,
            String sortBy,
            Category category,
            Long hostId,
            Long countryId,
            Integer numRooms,
            Boolean hasAvailableRooms) {
        
        // Build sort orders - support multiple sort fields
        List<Sort.Order> orders = new ArrayList<>();
        if (sortBy != null && !sortBy.isEmpty()) {
            String[] sortFields = sortBy.split(",");
            for (String field : sortFields) {
                if (field.startsWith("-")) {
                    orders.add(Sort.Order.desc(field.substring(1)));
                } else {
                    orders.add(Sort.Order.asc(field));
                }
            }
        } else {
            orders.add(Sort.Order.asc("name"));
        }
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        return accomodationRepository.findAllWithFilters(category, hostId, countryId, numRooms, hasAvailableRooms, pageable);
    }

    @Override
    public Page<AccommodationExtendedProjection> findAllExtendedProjection(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return accomodationRepository.findAllExtendedProjection(pageable);
    }

    @Override
    public Page<AccommodationProjection> findAllProjection(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return accomodationRepository.findAllProjection(pageable);
    }

    @Override
    public Accomodation create(Accomodation accommodation) {
        return accomodationRepository.save(accommodation);
    }

    @Override
    public Optional<Accomodation> update(Long id, Accomodation accommodation) {

        return accomodationRepository
                .findById(id)
                .map((existingAccommodation) -> {
                    existingAccommodation.setName(accommodation.getName());
                    existingAccommodation.setCategory(accommodation.getCategory());
                    existingAccommodation.setHost(accommodation.getHost());
                    existingAccommodation.setNumRooms(accommodation.getNumRooms());
                    existingAccommodation.setCondition(accommodation.getCondition());
                    existingAccommodation.setOccupied(accommodation.getOccupied());
                    return accomodationRepository.save(existingAccommodation);
                });
    }

    @Override
    public Optional<Accomodation> deleteById(Long id) {
        Optional<Accomodation> accommodation = accomodationRepository.findById(id);
        accommodation.ifPresent(accomodationRepository::delete);
        return accommodation;
    }

    @Override
    public void addReview(Long accommodationId, String comment, Double rating) {
        Review review = new Review(comment, rating);
        review.setAccomodation(accomodationRepository.findById(accommodationId).orElseThrow(() -> new RuntimeException("Accommodation not found")));

        accomodationRepository.findById(accommodationId).ifPresent(accommodation -> {
            accommodation.getReviews().add(review);
            accomodationRepository.save(accommodation);
        });
    }

    // ========== EntityGraph Optimized Methods ==========

    @Override
    public List<Accomodation> findAllWithHost() {
        return accomodationRepository.findAllWithHost();
    }

    @Override
    public List<Accomodation> findAllWithHostAndCountry() {
        return accomodationRepository.findAllWithHostAndCountry();
    }
    
    @Override
    public Optional<Accomodation> rentAccommodation(Long accommodationId) {
        return rentAccommodation(accommodationId, 1);
    }
    
    @Override
    public Optional<Accomodation> rentAccommodation(Long accommodationId, Integer numberOfRoomsToRent) {
        if (numberOfRoomsToRent == null || numberOfRoomsToRent <= 0) {
            numberOfRoomsToRent = 1;
        }
        
        try {
            // Get accommodation with host and country details
            Optional<Accomodation> accommodationOpt = accomodationRepository.findByIdWithDetails(accommodationId);
            
            if (accommodationOpt.isEmpty()) {
                log.warn("Attempted to rent non-existent accommodation with ID: {}", accommodationId);
                throw new IllegalArgumentException("Accommodation not found with ID: " + accommodationId);
            }
            
            Accomodation accommodation = accommodationOpt.get();
            
            // Check if accommodation is already fully occupied
            if (accommodation.getOccupied() != null && accommodation.getOccupied()) {
                log.warn("Attempted to rent fully occupied accommodation: {}", accommodationId);
                throw new IllegalArgumentException("Accommodation is fully occupied: " + accommodation.getName());
            }
            
            // Mark as occupied (simplified - assuming all rooms are rented at once)
            accommodation.setOccupied(true);
            Accomodation updatedAccommodation = accomodationRepository.save(accommodation);
            
            log.info("Accommodation {} rented successfully", accommodationId);
            
            // Publish event
            String hostName = accommodation.getHost() != null ? 
                    accommodation.getHost().getName() + " " + accommodation.getHost().getSurname() : "Unknown";
            String countryName = accommodation.getHost() != null && accommodation.getHost().getCountry() != null ?
                    accommodation.getHost().getCountry().getName() : "Unknown";
            
            eventPublisher.publishAccommodationRented(
                    updatedAccommodation,
                    0, // All rooms are now occupied
                    hostName,
                    countryName
            );
            
            return Optional.of(updatedAccommodation);
        } catch (Exception e) {
            log.error("Error renting accommodation with ID: {}", accommodationId, e);
            throw new RuntimeException("Failed to rent accommodation", e);
        }
    }
}
