package mk.ukim.finki.emt.lab1_groupb_emt.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entity representing a fully occupied accommodation tracking record.
 * Stores information about when accommodations become fully booked
 * and when they are expected to have vacancies.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fully_occupied_accommodations")
public class FullyOccupiedAccommodation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "accommodation_id", nullable = false)
    private Long accommodationId;
    
    @Column(name = "accommodation_name")
    private String accommodationName;
    
    @Column(name = "category")
    private String category;
    
    @Column(name = "total_rooms")
    private Integer totalRooms;
    
    @Column(name = "host_name")
    private String hostName;
    
    @Column(name = "host_email")
    private String hostEmail;
    
    @Column(name = "country_name")
    private String countryName;
    
    @Column(name = "fully_occupied_at", nullable = false)
    private LocalDateTime fullyOccupiedAt;
    
    @Column(name = "expected_vacancy_date")
    private LocalDateTime expectedVacancyDate;
    
    @Column(name = "status", nullable = false, length = 20)
    private String status;  // OCCUPIED, VACANT, ARCHIVED
    
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    /**
     * Constructor for creating a new fully occupied accommodation record.
     */
    public FullyOccupiedAccommodation(
            Long accommodationId,
            String accommodationName,
            String category,
            Integer totalRooms,
            String hostName,
            String hostEmail,
            String countryName) {
        this.accommodationId = accommodationId;
        this.accommodationName = accommodationName;
        this.category = category;
        this.totalRooms = totalRooms;
        this.hostName = hostName;
        this.hostEmail = hostEmail;
        this.countryName = countryName;
        this.fullyOccupiedAt = LocalDateTime.now();
        this.status = "OCCUPIED";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}

