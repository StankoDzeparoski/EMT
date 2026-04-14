package mk.ukim.finki.emt.lab1_groupb_emt.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for most popular hosts statistics.
 * Contains host details with total rent count and accommodation count information.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PopularHostDto {
    
    /**
     * Host ID
     */
    private Long hostId;
    
    /**
     * Host's first name
     */
    private String hostName;
    
    /**
     * Host's surname/last name
     */
    private String hostSurname;
    
    /**
     * Host's email address
     */
    private String email;
    
    /**
     * Name of the country where the host is located
     */
    private String countryName;
    
    /**
     * Total number of times accommodations of this host have been rented
     */
    private Long totalRentCount;
    
    /**
     * Total number of accommodations owned by this host
     */
    private Long totalAccommodations;
}

