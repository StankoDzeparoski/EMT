package mk.ukim.finki.emt.lab1_groupb_emt.event.listener;

import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.emt.lab1_groupb_emt.event.AccommodationRentedEvent;
import mk.ukim.finki.emt.lab1_groupb_emt.event.publisher.AccommodationFullyOccupiedEventPublisher;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.AccommodationActivityLog;
import mk.ukim.finki.emt.lab1_groupb_emt.repository.AccommodationActivityLogRepository;
import mk.ukim.finki.emt.lab1_groupb_emt.repository.AccomodationRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Event listener for accommodation rental events.
 * Listens to AccommodationRentedEvent and handles multiple aspects:
 * 1. Logging - Writes log messages about the rental
 * 2. Activity Tracking - Records the activity in the database
 * 3. Alerts - Special handling when accommodation becomes fully occupied
 */
@Component
@Slf4j
public class AccommodationRentedEventListener {
    
    private final AccommodationActivityLogRepository activityLogRepository;
    private final AccommodationFullyOccupiedEventPublisher fullyOccupiedEventPublisher;
    private final AccomodationRepository accommodationRepository;

    public AccommodationRentedEventListener(
            AccommodationActivityLogRepository activityLogRepository,
            AccommodationFullyOccupiedEventPublisher fullyOccupiedEventPublisher,
            AccomodationRepository accommodationRepository) {
        this.activityLogRepository = activityLogRepository;
        this.fullyOccupiedEventPublisher = fullyOccupiedEventPublisher;
        this.accommodationRepository = accommodationRepository;
    }

    /**
     * Handles accommodation rented events.
     * This method is asynchronous to avoid blocking the rental process.
     *
     * @param event the accommodation rented event
     */
    @EventListener
    @Async
    @Transactional
    public void handleAccommodationRented(AccommodationRentedEvent event) {
        log.info("Received AccommodationRentedEvent for accommodation: {}", event.getAccommodationId());
        
        // 1. Log the rental event
        logRentalEvent(event);
        
        // 2. Record activity in database
        recordActivityLog(event);
        
        // 3. Check if fully occupied and handle specially
        if (event.isFullyOccupied()) {
            handleFullyOccupied(event);
        }
    }

    /**
     * Logs the rental event with relevant details.
     *
     * @param event the accommodation rented event
     */
    private void logRentalEvent(AccommodationRentedEvent event) {
        log.info(
                "Accommodation '{}' (ID: {}) has been rented. " +
                "Category: {}, Host: {}, Country: {}, " +
                "Total Rooms: {}, Remaining Available: {}",
                event.getAccommodationName(),
                event.getAccommodationId(),
                event.getCategory(),
                event.getHostName(),
                event.getCountryName(),
                event.getTotalRooms(),
                event.getRemainingAvailableRooms()
        );
        
        log.debug(
                "Rental Event Details - Timestamp: {}, Fully Occupied: {}",
                event.getRentalTime(),
                event.isFullyOccupied()
        );
    }

    /**
     * Records the rental activity in the database.
     *
     * @param event the accommodation rented event
     */
    private void recordActivityLog(AccommodationRentedEvent event) {
        try {
            String activityType = "RENTED";
            String description = String.format(
                    "Accommodation '%s' rented. Remaining rooms: %d/%d",
                    event.getAccommodationName(),
                    event.getRemainingAvailableRooms(),
                    event.getTotalRooms()
            );
            
            AccommodationActivityLog activityLog = new AccommodationActivityLog(
                    event.getAccommodationId(),
                    activityType,
                    description,
                    event.getTotalRooms(),
                    event.getRemainingAvailableRooms()
            );
            
            activityLogRepository.save(activityLog);
            log.info("Activity log recorded for accommodation: {}", event.getAccommodationId());
        } catch (Exception e) {
            log.error("Failed to record activity log for accommodation: {}", 
                    event.getAccommodationId(), e);
        }
    }

    /**
     * Handles the special case when an accommodation becomes fully occupied (no available rooms).
     *
     * @param event the accommodation rented event
     */
    private void handleFullyOccupied(AccommodationRentedEvent event) {
        log.warn(
                "ACCOMMODATION FULLY OCCUPIED! " +
                "Accommodation '{}' (ID: {}) has no available rooms remaining. " +
                "Category: {}, Host: {}, Country: {}",
                event.getAccommodationName(),
                event.getAccommodationId(),
                event.getCategory(),
                event.getHostName(),
                event.getCountryName()
        );
        
        try {
            String fullyOccupiedDescription = String.format(
                    "Accommodation '%s' is now FULLY OCCUPIED. All %d rooms are rented.",
                    event.getAccommodationName(),
                    event.getTotalRooms()
            );
            
            AccommodationActivityLog fullyOccupiedLog = new AccommodationActivityLog(
                    event.getAccommodationId(),
                    "FULLY_OCCUPIED",
                    fullyOccupiedDescription,
                    event.getTotalRooms(),
                    0
            );
            
            activityLogRepository.save(fullyOccupiedLog);
            log.warn("FULLY_OCCUPIED event recorded for accommodation: {}", 
                    event.getAccommodationId());
            
            // Publish specialized fully occupied event
            var accommodation = accommodationRepository.findByIdWithDetails(event.getAccommodationId());
            if (accommodation.isPresent()) {
                fullyOccupiedEventPublisher.publishAccommodationFullyOccupied(
                        accommodation.get(),
                        event.getHostName(),
                        "",  // email (not available in this event)
                        event.getCountryName(),
                        null  // daysUntilExpected
                );
            }
            
        } catch (Exception e) {
            log.error("Failed to record FULLY_OCCUPIED event for accommodation: {}", 
                    event.getAccommodationId(), e);
        }
    }
}

