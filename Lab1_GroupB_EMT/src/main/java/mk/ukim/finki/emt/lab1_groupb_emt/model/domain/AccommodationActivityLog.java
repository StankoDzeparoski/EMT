package mk.ukim.finki.emt.lab1_groupb_emt.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entity representing an activity log entry for accommodation rental events.
 * Tracks when accommodations are rented, what the rental status is, and details about the rental.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accommodation_activity_log")
public class AccommodationActivityLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "accommodation_id", nullable = false)
    private Long accommodationId;
    
    @Column(name = "activity_type", nullable = false, length = 50)
    private String activityType;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "total_rooms")
    private Integer totalRooms;
    
    @Column(name = "available_rooms")
    private Integer availableRooms;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    public AccommodationActivityLog(
            Long accommodationId,
            String activityType,
            String description,
            Integer totalRooms,
            Integer availableRooms) {
        this.accommodationId = accommodationId;
        this.activityType = activityType;
        this.description = description;
        this.totalRooms = totalRooms;
        this.availableRooms = availableRooms;
        this.createdAt = LocalDateTime.now();
    }
}

