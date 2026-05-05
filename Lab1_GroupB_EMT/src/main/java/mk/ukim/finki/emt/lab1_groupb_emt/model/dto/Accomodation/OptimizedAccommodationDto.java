package mk.ukim.finki.emt.lab1_groupb_emt.model.dto.Accomodation;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Accomodation;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Category;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Condition;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for displaying optimized accommodation data with host and country information.
 * Uses EntityGraph for efficient loading - prevents N+1 query problem.
 * 
 * Nested DTOs:
 * - Accommodation contains Host
 * - Host contains Country
 */
public record OptimizedAccommodationDto(
        Long id,
        String name,
        Category category,
        Condition condition,
        Integer numRooms,
        Boolean occupied,
        LocalDateTime createdAt,
        HostInfoDto host,
        Double averageRating
) {
    /**
     * DTO for host information (nested in OptimizedAccommodationDto)
     */
    public record HostInfoDto(
            Long id,
            String name,
            String surname,
            CountryInfoDto country
    ) {}

    /**
     * DTO for country information (nested in HostInfoDto)
     */
    public record CountryInfoDto(
            Long id,
            String name,
            String continent
    ) {}

    /**
     * Convert Accomodation entity to OptimizedAccommodationDto
     * Assumes host and country are already loaded via EntityGraph
     */
    public static OptimizedAccommodationDto from(Accomodation accommodation) {
        Double avgRating = accommodation.getReviews()
                .stream()
                .mapToDouble(review -> review.getRating())
                .average()
                .orElse(0.0);

        HostInfoDto hostInfoDto = null;
        if (accommodation.getHost() != null) {
            CountryInfoDto countryInfoDto = null;
            if (accommodation.getHost().getCountry() != null) {
                countryInfoDto = new CountryInfoDto(
                        accommodation.getHost().getCountry().getId(),
                        accommodation.getHost().getCountry().getName(),
                        accommodation.getHost().getCountry().getContinent()
                );
            }
            hostInfoDto = new HostInfoDto(
                    accommodation.getHost().getId(),
                    accommodation.getHost().getName(),
                    accommodation.getHost().getSurname(),
                    countryInfoDto
            );
        }

        return new OptimizedAccommodationDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getCondition(),
                accommodation.getNumRooms(),
                accommodation.getOccupied(),
                accommodation.getCreatedAt(),
                hostInfoDto,
                avgRating
        );
    }

    /**
     * Convert list of Accomodation entities to list of OptimizedAccommodationDto
     */
    public static List<OptimizedAccommodationDto> from(List<Accomodation> accommodations) {
        return accommodations.stream()
                .map(OptimizedAccommodationDto::from)
                .collect(Collectors.toList());
    }
}

