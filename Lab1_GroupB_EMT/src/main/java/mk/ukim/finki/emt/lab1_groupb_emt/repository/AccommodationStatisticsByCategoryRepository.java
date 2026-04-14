package mk.ukim.finki.emt.lab1_groupb_emt.repository;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.AccommodationStatisticsByCategory;
import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.AccommodationStatisticsByCategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for read-only access to the accommodation_statistics_by_category materialized view.
 * This repository provides methods to query aggregated accommodation statistics by category.
 */
@Repository
public interface AccommodationStatisticsByCategoryRepository 
        extends JpaRepository<AccommodationStatisticsByCategory, String> {
    
    /**
     * Find all accommodation statistics ordered by category.
     *
     * @return list of all accommodation statistics
     */
    @Query("SELECT stats FROM AccommodationStatisticsByCategory stats ORDER BY stats.category")
    List<AccommodationStatisticsByCategoryProjection> findAllStatistics();
    
    /**
     * Find statistics for a specific category.
     *
     * @param category the accommodation category
     * @return statistics for the specified category
     */
    Optional<AccommodationStatisticsByCategoryProjection> findByCategory(String category);
    
    /**
     * Find all statistics with at least a minimum number of accommodations.
     *
     * @param minAccommodations minimum number of accommodations
     * @return list of statistics meeting the criteria
     */
    @Query("SELECT stats FROM AccommodationStatisticsByCategory stats " +
           "WHERE stats.totalAccommodations >= :minAccommodations " +
           "ORDER BY stats.totalAccommodations DESC")
    List<AccommodationStatisticsByCategoryProjection> findByMinimumAccommodations(Long minAccommodations);
    
    /**
     * Refresh the materialized view.
     * This method executes native SQL to refresh the materialized view.
     */
    @Modifying
    @Query(value = "REFRESH MATERIALIZED VIEW accommodation_statistics_by_category", nativeQuery = true)
    void refresh();
    
    /**
     * Refresh the materialized view concurrently (without blocking reads).
     * This method executes native SQL to refresh the materialized view without locks.
     */
    @Modifying
    @Query(value = "REFRESH MATERIALIZED VIEW CONCURRENTLY accommodation_statistics_by_category", nativeQuery = true)
    void refreshConcurrently();
}

