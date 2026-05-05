package mk.ukim.finki.emt.lab1_groupb_emt.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

/**
 * Event that is published when an accommodation is rented (booked/occupied).
 * This event is fired when the number of available rooms in an accommodation
 * is reduced or when the accommodation is marked as occupied.
 */
@Getter
public class AccommodationRentedEvent extends ApplicationEvent {
    
    private final Long accommodationId;
    private final String accommodationName;
    private final String category;
    private final Integer totalRooms;
    private final Integer remainingAvailableRooms;
    private final String hostName;
    private final String countryName;
    private final LocalDateTime rentalTime;
    private final boolean fullyOccupied;

    public AccommodationRentedEvent(
            Object source,
            Long accommodationId,
            String accommodationName,
            String category,
            Integer totalRooms,
            Integer remainingAvailableRooms,
            String hostName,
            String countryName,
            LocalDateTime rentalTime,
            boolean fullyOccupied) {
        super(source);
        this.accommodationId = accommodationId;
        this.accommodationName = accommodationName;
        this.category = category;
        this.totalRooms = totalRooms;
        this.remainingAvailableRooms = remainingAvailableRooms;
        this.hostName = hostName;
        this.countryName = countryName;
        this.rentalTime = rentalTime;
        this.fullyOccupied = fullyOccupied;
    }
}

