package mk.ukim.finki.emt.lab1_groupb_emt.repository;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Accomodation;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Category;
import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.AccommodationExtendedProjection;
import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.AccommodationProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Long> {

    @Query("SELECT a FROM Accomodation a WHERE " +
            "(:category IS NULL OR a.category = :category) AND " +
            "(:hostId IS NULL OR a.host.id = :hostId) AND " +
            "(:countryId IS NULL OR a.host.country.id = :countryId) AND " +
            "(:numRooms IS NULL OR a.numRooms = :numRooms) AND " +
            "(:hasAvailableRooms IS NULL OR " +
            "  (:hasAvailableRooms = true AND a.occupied = false) OR " +
            "  (:hasAvailableRooms = false))")
    Page<Accomodation> findAllWithFilters(
            @Param("category") Category category,
            @Param("hostId") Long hostId,
            @Param("countryId") Long countryId,
            @Param("numRooms") Integer numRooms,
            @Param("hasAvailableRooms") Boolean hasAvailableRooms,
            Pageable pageable);

    // Projection: Basic accommodation info (id, name, category, numRooms)
    @Query(nativeQuery = true, 
           value = "SELECT a.id, a.name, a.category, a.num_rooms FROM accommodations a")
    Page<AccommodationProjection> findAllProjection(Pageable pageable);

    // Projection: Extended accommodation info with host and country details
    @Query(nativeQuery = true,
           value = "SELECT " +
                   "a.id, " +
                   "a.name, " +
                   "a.category, " +
                   "a.num_rooms, " +
                   "h.name AS hostName, " +
                   "h.surname AS hostSurname, " +
                   "c.name AS countryName " +
                   "FROM accommodations a " +
                   "INNER JOIN hosts h ON a.host_id = h.id " +
                   "INNER JOIN countries c ON h.country_id = c.id")
    Page<AccommodationExtendedProjection> findAllExtendedProjection(Pageable pageable);

    // ========== EntityGraph Optimized Methods ==========

    /**
     * Endpoint 1: Find all accommodations with host loaded eagerly.
     * Single query with JOIN to hosts table - avoids N+1 problem.
     */
    @EntityGraph(attributePaths = {"host"})
    @Query("SELECT a FROM Accomodation a")
    List<Accomodation> findAllWithHost();

    /**
     * Endpoint 2: Find all accommodations with host and country loaded eagerly.
     * Single query with JOINs to hosts and countries tables - avoids N+1 problem.
     */
    @EntityGraph(attributePaths = {"host", "host.country"})
    @Query("SELECT a FROM Accomodation a")
    List<Accomodation> findAllWithHostAndCountry();
    
    /**
     * Find accommodation by ID with host and country loaded eagerly.
     * Used for renting operations to have all necessary data.
     */
    @EntityGraph(attributePaths = {"host", "host.country"})
    @Query("SELECT a FROM Accomodation a WHERE a.id = :id")
    java.util.Optional<Accomodation> findByIdWithDetails(@Param("id") Long id);
}
