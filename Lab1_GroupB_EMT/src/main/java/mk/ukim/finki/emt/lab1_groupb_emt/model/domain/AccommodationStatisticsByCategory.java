package mk.ukim.finki.emt.lab1_groupb_emt.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * Read-only entity representing the accommodation_statistics_by_category materialized view.
 * This entity maps to a materialized view that provides aggregated statistics about
 * accommodations grouped by category.
 *
 * Materialized view contains: category, total accommodations, total rooms, average rooms per accommodation
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accommodation_statistics_by_category")
public class AccommodationStatisticsByCategory {
    
    @Id
    private String category;
    
    private Long totalAccommodations;
    
    private Integer totalRooms;
    
    private BigDecimal avgRoomsPerAccommodation;
}

