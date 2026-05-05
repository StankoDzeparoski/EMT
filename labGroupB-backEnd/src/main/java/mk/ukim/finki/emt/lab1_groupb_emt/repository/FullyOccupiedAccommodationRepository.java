package mk.ukim.finki.emt.lab1_groupb_emt.repository;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.FullyOccupiedAccommodation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository for managing fully occupied accommodation tracking records.
 */
@Repository
public interface FullyOccupiedAccommodationRepository extends JpaRepository<FullyOccupiedAccommodation, Long> {
    
    /**
     * Find a fully occupied record for a specific accommodation.
     *
     * @param accommodationId the accommodation ID
     * @return optional fully occupied record
     */
    Optional<FullyOccupiedAccommodation> findByAccommodationIdAndStatus(
            Long accommodationId, 
            String status);
    
    /**
     * Find all currently occupied accommodations.
     *
     * @param status the status filter (OCCUPIED, VACANT, ARCHIVED)
     * @param pageable pagination information
     * @return page of fully occupied records
     */
    Page<FullyOccupiedAccommodation> findByStatus(String status, Pageable pageable);
    
    /**
     * Find fully occupied accommodations by expected vacancy date range.
     *
     * @param startDate start date
     * @param endDate end date
     * @param pageable pagination information
     * @return page of records
     */
    Page<FullyOccupiedAccommodation> findByExpectedVacancyDateBetween(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable);
    
    /**
     * Find fully occupied accommodations by category.
     *
     * @param category the accommodation category
     * @param pageable pagination information
     * @return page of records
     */
    Page<FullyOccupiedAccommodation> findByCategory(String category, Pageable pageable);
    
    /**
     * Find recently fully occupied accommodations.
     *
     * @param startDate start date for filtering
     * @param pageable pagination information
     * @return page of records
     */
    @Query("SELECT f FROM FullyOccupiedAccommodation f WHERE f.fullyOccupiedAt >= :startDate ORDER BY f.fullyOccupiedAt DESC")
    Page<FullyOccupiedAccommodation> findRecentlyFullyOccupied(
            @Param("startDate") LocalDateTime startDate,
            Pageable pageable);
    
    /**
     * Count currently occupied accommodations.
     *
     * @return number of occupied accommodations
     */
    long countByStatus(String status);
}

