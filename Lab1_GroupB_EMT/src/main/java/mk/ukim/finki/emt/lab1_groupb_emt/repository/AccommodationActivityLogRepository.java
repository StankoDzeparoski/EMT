package mk.ukim.finki.emt.lab1_groupb_emt.repository;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.AccommodationActivityLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Repository for managing accommodation activity log entries.
 */
@Repository
public interface AccommodationActivityLogRepository extends JpaRepository<AccommodationActivityLog, Long> {
    
    /**
     * Find all activity logs for a specific accommodation.
     *
     * @param accommodationId the accommodation ID
     * @param pageable pagination information
     * @return page of activity logs
     */
    Page<AccommodationActivityLog> findByAccommodationId(Long accommodationId, Pageable pageable);
    
    /**
     * Find activity logs by activity type.
     *
     * @param activityType the type of activity
     * @param pageable pagination information
     * @return page of activity logs
     */
    Page<AccommodationActivityLog> findByActivityType(String activityType, Pageable pageable);
    
    /**
     * Find activity logs created within a time range.
     *
     * @param startTime start of the range
     * @param endTime end of the range
     * @param pageable pagination information
     * @return page of activity logs
     */
    Page<AccommodationActivityLog> findByCreatedAtBetween(LocalDateTime startTime, LocalDateTime endTime, Pageable pageable);
    
    /**
     * Find all activity logs for a specific accommodation and activity type.
     *
     * @param accommodationId the accommodation ID
     * @param activityType the type of activity
     * @param pageable pagination information
     * @return page of activity logs
     */
    Page<AccommodationActivityLog> findByAccommodationIdAndActivityType(
            Long accommodationId,
            String activityType,
            Pageable pageable);
    
    /**
     * Find recent activity logs (last N entries).
     *
     * @param pageable pagination information with limit
     * @return list of recent activity logs
     */
    List<AccommodationActivityLog> findFirstByOrderByCreatedAtDesc(Pageable pageable);
    
    /**
     * Get most popular accommodations by rent count.
     * Returns native query result with accommodation statistics.
     *
     * @param activityType the activity type to count (usually "RENTED")
     * @param pageable pagination information
     * @return list of accommodation rent statistics
     */
    @Query(value = """
            SELECT 
                a.id as accommodationId,
                a.name as accommodationName,
                a.category,
                COUNT(aal.id) as rentCount,
                h.name as hostName,
                h.surname as hostSurname
            FROM accommodations a
            LEFT JOIN accommodation_activity_log aal ON a.id = aal.accommodation_id 
                AND aal.activity_type = :activityType
            LEFT JOIN hosts h ON a.host_id = h.id
            GROUP BY a.id, a.name, a.category, h.name, h.surname
            ORDER BY rentCount DESC
            """, nativeQuery = true)
    List<Map<String, Object>> findMostPopularAccommodations(
            @Param("activityType") String activityType,
            Pageable pageable);
    
    /**
     * Get most popular hosts by total rent count of their accommodations.
     * Returns native query result with host statistics.
     *
     * @param activityType the activity type to count (usually "RENTED")
     * @param pageable pagination information
     * @return list of host rent statistics
     */
    @Query(value = """
            SELECT 
                h.id as hostId,
                h.name as hostName,
                h.surname as hostSurname,
                h.email,
                c.name as countryName,
                COUNT(DISTINCT aal.id) as totalRentCount,
                COUNT(DISTINCT a.id) as totalAccommodations
            FROM hosts h
            LEFT JOIN accommodations a ON h.id = a.host_id
            LEFT JOIN accommodation_activity_log aal ON a.id = aal.accommodation_id 
                AND aal.activity_type = :activityType
            LEFT JOIN countries c ON h.country_id = c.id
            GROUP BY h.id, h.name, h.surname, h.email, c.name
            ORDER BY totalRentCount DESC
            """, nativeQuery = true)
    List<Map<String, Object>> findMostPopularHosts(
            @Param("activityType") String activityType,
            Pageable pageable);
}

