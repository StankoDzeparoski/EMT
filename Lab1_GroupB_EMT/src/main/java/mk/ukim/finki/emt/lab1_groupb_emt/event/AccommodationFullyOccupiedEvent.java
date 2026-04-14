package mk.ukim.finki.emt.lab1_groupb_emt.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

/**
 * Event that is published when an accommodation becomes fully occupied (no available rooms).
 * This event is fired specifically when the last available room is rented,
 * indicating the accommodation is now completely booked with no vacancies.
 */
@Getter
public class AccommodationFullyOccupiedEvent extends ApplicationEvent {
    
    private final Long accommodationId;
    private final String accommodationName;
    private final String category;
    private final Integer totalRooms;
    private final String hostName;
    private final String hostEmail;
    private final String countryName;
    private final LocalDateTime fullyOccupiedTime;
    private final Integer daysUntilExpected;  // Expected vacancy (null if unknown)

    public AccommodationFullyOccupiedEvent(
            Object source,
            Long accommodationId,
            String accommodationName,
            String category,
            Integer totalRooms,
            String hostName,
            String hostEmail,
            String countryName,
            LocalDateTime fullyOccupiedTime,
            Integer daysUntilExpected) {
        super(source);
        this.accommodationId = accommodationId;
        this.accommodationName = accommodationName;
        this.category = category;
        this.totalRooms = totalRooms;
        this.hostName = hostName;
        this.hostEmail = hostEmail;
        this.countryName = countryName;
        this.fullyOccupiedTime = fullyOccupiedTime;
        this.daysUntilExpected = daysUntilExpected;
    }
}

