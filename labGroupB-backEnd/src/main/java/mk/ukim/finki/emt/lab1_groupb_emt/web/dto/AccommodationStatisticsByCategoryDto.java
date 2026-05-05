package mk.ukim.finki.emt.lab1_groupb_emt.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * DTO for accommodation statistics by category.
 * Contains aggregated data about accommodations grouped by category.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationStatisticsByCategoryDto {
    
    /**
     * The accommodation category (e.g., ROOM, HOUSE, FLAT, APARTMENT, HOTEL, MOTEL)
     */
    private String category;
    
    /**
     * Total number of accommodations in this category
     */
    private Long totalAccommodations;
    
    /**
     * Total number of rooms in all accommodations of this category
     */
    private Integer totalRooms;
    
    /**
     * Average number of rooms per accommodation in this category (rounded to 2 decimal places)
     */
    private BigDecimal avgRoomsPerAccommodation;
}

