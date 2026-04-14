package mk.ukim.finki.emt.lab1_groupb_emt.event.listener;

import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.emt.lab1_groupb_emt.event.AccommodationFullyOccupiedEvent;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.FullyOccupiedAccommodation;
import mk.ukim.finki.emt.lab1_groupb_emt.repository.FullyOccupiedAccommodationRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Event listener for fully occupied accommodation events.
 * Listens to AccommodationFullyOccupiedEvent and handles:
 * 1. Logging - Writes alert log messages
 * 2. Database Tracking - Records fully occupied status
 * 3. Status Management - Maintains occupancy tracking records
 */
@Component
@Slf4j
public class AccommodationFullyOccupiedEventListener {
    
    private final FullyOccupiedAccommodationRepository fullyOccupiedRepository;

    public AccommodationFullyOccupiedEventListener(FullyOccupiedAccommodationRepository fullyOccupiedRepository) {
        this.fullyOccupiedRepository = fullyOccupiedRepository;
    }

    /**
     * Handles accommodation fully occupied events.
     * This method is asynchronous to avoid blocking the rental process.
     *
     * @param event the accommodation fully occupied event
     */
    @EventListener
    @Async
    @Transactional
    public void handleAccommodationFullyOccupied(AccommodationFullyOccupiedEvent event) {
        log.info("Received AccommodationFullyOccupiedEvent for accommodation: {}", event.getAccommodationId());
        
        // 1. Log the fully occupied event with alert level
        logFullyOccupiedAlert(event);
        
        // 2. Record fully occupied status in database
        recordFullyOccupiedStatus(event);
        
        // 3. Notify host (future feature)
        notifyHost(event);
    }

    /**
     * Logs the fully occupied alert with all relevant details.
     *
     * @param event the accommodation fully occupied event
     */
    private void logFullyOccupiedAlert(AccommodationFullyOccupiedEvent event) {
        log.warn(
                "╔══════════════════════════════════════════════════════╗\n" +
                "║ ⚠️  ACCOMMODATION FULLY OCCUPIED ALERT ⚠️             ║\n" +
                "║  Accommodation: '{}' (ID: {})\n" +
                "║  Category: {}\n" +
                "║  Total Rooms: {}\n" +
                "║  Host: {}\n" +
                "║  Country: {}\n" +
                "║  Occupied at: {}\n" +
                "╚══════════════════════════════════════════════════════╝",
                event.getAccommodationName(),
                event.getAccommodationId(),
                event.getCategory(),
                event.getTotalRooms(),
                event.getHostName(),
                event.getCountryName(),
                event.getFullyOccupiedTime()
        );
        
        log.debug(
                "Fully Occupied Event Details - Host Email: {}, Expected Vacancy: {} days",
                event.getHostEmail(),
                event.getDaysUntilExpected()
        );
    }

    /**
     * Records the fully occupied status in the database.
     * Creates or updates a tracking record for this accommodation.
     *
     * @param event the accommodation fully occupied event
     */
    private void recordFullyOccupiedStatus(AccommodationFullyOccupiedEvent event) {
        try {
            // Check if record already exists
            var existingRecord = fullyOccupiedRepository.findByAccommodationIdAndStatus(
                    event.getAccommodationId(),
                    "OCCUPIED"
            );
            
            if (existingRecord.isPresent()) {
                log.info("Fully occupied record already exists for accommodation: {}", 
                        event.getAccommodationId());
                return;
            }
            
            // Create new fully occupied record
            LocalDateTime expectedVacancy = null;
            if (event.getDaysUntilExpected() != null) {
                expectedVacancy = LocalDateTime.now().plusDays(event.getDaysUntilExpected());
            }
            
            FullyOccupiedAccommodation fullyOccupiedRecord = new FullyOccupiedAccommodation(
                    event.getAccommodationId(),
                    event.getAccommodationName(),
                    event.getCategory(),
                    event.getTotalRooms(),
                    event.getHostName(),
                    event.getHostEmail(),
                    event.getCountryName()
            );
            
            fullyOccupiedRecord.setFullyOccupiedAt(event.getFullyOccupiedTime());
            fullyOccupiedRecord.setExpectedVacancyDate(expectedVacancy);
            fullyOccupiedRecord.setStatus("OCCUPIED");
            fullyOccupiedRecord.setNotes(String.format(
                    "Fully occupied with %d rooms. Expecting vacancy in %d days.",
                    event.getTotalRooms(),
                    event.getDaysUntilExpected() != null ? event.getDaysUntilExpected() : 0
            ));
            
            fullyOccupiedRepository.save(fullyOccupiedRecord);
            log.info("Fully occupied status recorded for accommodation: {} (ID: {})", 
                    event.getAccommodationName(), event.getAccommodationId());
            
        } catch (Exception e) {
            log.error("Failed to record fully occupied status for accommodation: {}", 
                    event.getAccommodationId(), e);
        }
    }

    /**
     * Notifies the host about the accommodation being fully occupied.
     * (Future feature for email/SMS notifications)
     *
     * @param event the accommodation fully occupied event
     */
    private void notifyHost(AccommodationFullyOccupiedEvent event) {
        try {
            log.info("Preparing host notification for: {} (Email: {})", 
                    event.getHostName(), event.getHostEmail());
            
            // TODO: Implement email notification
            // emailService.sendFullyOccupiedNotification(event.getHostEmail(), event);
            
            // TODO: Implement SMS notification
            // smsService.sendFullyOccupiedAlert(event.getHostName());
            
        } catch (Exception e) {
            log.error("Failed to notify host for accommodation: {}", 
                    event.getAccommodationId(), e);
        }
    }
}

