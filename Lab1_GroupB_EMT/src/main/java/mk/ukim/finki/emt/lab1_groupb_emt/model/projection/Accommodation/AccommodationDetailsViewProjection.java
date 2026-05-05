package mk.ukim.finki.emt.lab1_groupb_emt.model.projection.Accommodation;

/**
 * Read-only projection for accommodation details view.
 * This projection is optimized for displaying simplified accommodation information.
 */
public interface AccommodationDetailsViewProjection {
    
    Long getAccommodationId();
    
    String getAccommodationName();
    
    String getCategory();
    
    Integer getNumRooms();
    
    String getHostFullName();
    
    String getCountryName();
}

