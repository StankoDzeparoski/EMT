package mk.ukim.finki.emt.lab1_groupb_emt.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Read-only entity representing the accommodation_details_view database view.
 * This entity maps to the accommodation_details_view which provides a simplified
 * view of accommodation information combined with host and country data.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "accommodation_details_view")
public class AccommodationDetailsView {
    
    @Id
    private Long accommodationId;
    
    private String accommodationName;
    
    private String category;
    
    private Integer numRooms;
    
    private String hostFullName;
    
    private String countryName;
}

