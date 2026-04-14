package mk.ukim.finki.emt.lab1_groupb_emt.event.publisher;

import mk.ukim.finki.emt.lab1_groupb_emt.event.AccommodationFullyOccupiedEvent;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Accomodation;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Publisher component for fully occupied accommodation events.
 * Responsible for publishing AccommodationFullyOccupiedEvent when accommodations become fully booked.
 */
@Component
public class AccommodationFullyOccupiedEventPublisher {
    
    private final ApplicationEventPublisher eventPublisher;

    public AccommodationFullyOccupiedEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    /**
     * Publishes a fully occupied event for an accommodation.
     *
     * @param accommodation the accommodation that is now fully occupied
     * @param hostName the full name of the host
     * @param hostEmail the email of the host
     * @param countryName the name of the country
     * @param daysUntilExpectedVacancy days until expected vacancy (nullable)
     */
    public void publishAccommodationFullyOccupied(
            Accomodation accommodation,
            String hostName,
            String hostEmail,
            String countryName,
            Integer daysUntilExpectedVacancy) {
        
        AccommodationFullyOccupiedEvent event = new AccommodationFullyOccupiedEvent(
                this,
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory() != null ? accommodation.getCategory().toString() : "UNKNOWN",
                accommodation.getNumRooms(),
                hostName,
                hostEmail,
                countryName,
                LocalDateTime.now(),
                daysUntilExpectedVacancy
        );
        
        eventPublisher.publishEvent(event);
    }
}

