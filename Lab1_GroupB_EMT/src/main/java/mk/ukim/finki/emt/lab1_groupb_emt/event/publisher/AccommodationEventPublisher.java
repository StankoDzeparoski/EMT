package mk.ukim.finki.emt.lab1_groupb_emt.event.publisher;

import mk.ukim.finki.emt.lab1_groupb_emt.event.AccommodationRentedEvent;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Accomodation;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Publisher component for accommodation events.
 * Responsible for publishing AccommodationRentedEvent when accommodations are rented.
 */
@Component
public class AccommodationEventPublisher {
    
    private final ApplicationEventPublisher eventPublisher;

    public AccommodationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    /**
     * Publishes an accommodation rented event.
     *
     * @param accommodation the accommodation that was rented
     * @param remainingAvailableRooms the number of remaining available rooms after rental
     * @param hostName the full name of the host
     * @param countryName the name of the country
     */
    public void publishAccommodationRented(
            Accomodation accommodation,
            Integer remainingAvailableRooms,
            String hostName,
            String countryName) {
        
        boolean fullyOccupied = remainingAvailableRooms != null && remainingAvailableRooms == 0;
        
        AccommodationRentedEvent event = new AccommodationRentedEvent(
                this,
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory() != null ? accommodation.getCategory().toString() : "UNKNOWN",
                accommodation.getNumRooms(),
                remainingAvailableRooms,
                hostName,
                countryName,
                LocalDateTime.now(),
                fullyOccupied
        );
        
        eventPublisher.publishEvent(event);
    }
}

