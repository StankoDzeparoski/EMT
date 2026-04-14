package mk.ukim.finki.emt.lab1_groupb_emt.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for most popular accommodations statistics.
 * Contains accommodation details with rent count information.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PopularAccommodationDto {
    
    /**
     * Accommodation ID
     */
    private Long accommodationId;
    
    /**
     * Accommodation name
     */
    private String accommodationName;
    
    /**
     * Accommodation category (HOTEL, APARTMENT, ROOM, etc.)
     */
    private String category;
    
    /**
     * Number of times this accommodation has been rented
     */
    private Long rentCount;
    
    /**
     * Host's first name
     */
    private String hostName;
    
    /**
     * Host's surname/last name
     */
    private String hostSurname;
}

