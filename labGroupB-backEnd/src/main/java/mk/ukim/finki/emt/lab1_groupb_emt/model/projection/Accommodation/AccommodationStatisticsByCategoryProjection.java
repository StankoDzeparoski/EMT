package mk.ukim.finki.emt.lab1_groupb_emt.model.projection.Accommodation;

import java.math.BigDecimal;

/**
 * Read-only projection for accommodation statistics by category.
 * This projection is optimized for displaying aggregated accommodation statistics.
 */
public interface AccommodationStatisticsByCategoryProjection {
    
    String getCategory();
    
    Long getTotalAccommodations();
    
    Integer getTotalRooms();
    
    BigDecimal getAvgRoomsPerAccommodation();
}

