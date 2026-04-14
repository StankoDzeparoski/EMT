package mk.ukim.finki.emt.lab1_groupb_emt.repository;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.AccommodationDetailsView;
import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.AccommodationDetailsViewProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for read-only access to the accommodation_details_view database view.
 * This repository provides methods to query the simplified accommodation view data.
 */
@Repository
public interface AccommodationDetailsViewRepository 
        extends JpaRepository<AccommodationDetailsView, Long> {
    
    /**
     * Get all accommodations from the view with pagination.
     *
     * @param pageable pagination information
     * @return a page of accommodation details
     */
    @Query("SELECT adv FROM AccommodationDetailsView adv")
    Page<AccommodationDetailsViewProjection> findAllProjection(Pageable pageable);
    
    /**
     * Search accommodations by accommodation name with pagination.
     *
     * @param accommodationName the name to search for
     * @param pageable pagination information
     * @return a page of matching accommodation details
     */
    @Query("SELECT adv FROM AccommodationDetailsView adv WHERE LOWER(adv.accommodationName) LIKE LOWER(CONCAT('%', :accommodationName, '%'))")
    Page<AccommodationDetailsViewProjection> findByAccommodationNameContainingIgnoreCase(
            @Param("accommodationName") String accommodationName, Pageable pageable);
    
    /**
     * Find accommodations by category with pagination.
     *
     * @param category the category filter
     * @param pageable pagination information
     * @return a page of accommodations in the specified category
     */
    @Query("SELECT adv FROM AccommodationDetailsView adv WHERE adv.category = :category")
    Page<AccommodationDetailsViewProjection> findByCategory(
            @Param("category") String category, Pageable pageable);
    
    /**
     * Find accommodations by host name with pagination.
     *
     * @param hostFullName the host name to search for
     * @param pageable pagination information
     * @return a page of accommodations by the specified host
     */
    @Query("SELECT adv FROM AccommodationDetailsView adv WHERE LOWER(adv.hostFullName) LIKE LOWER(CONCAT('%', :hostFullName, '%'))")
    Page<AccommodationDetailsViewProjection> findByHostFullNameContainingIgnoreCase(
            @Param("hostFullName") String hostFullName, Pageable pageable);
    
    /**
     * Find accommodations by country name with pagination.
     *
     * @param countryName the country name filter
     * @param pageable pagination information
     * @return a page of accommodations in the specified country
     */
    @Query("SELECT adv FROM AccommodationDetailsView adv WHERE LOWER(adv.countryName) LIKE LOWER(CONCAT('%', :countryName, '%'))")
    Page<AccommodationDetailsViewProjection> findByCountryNameContainingIgnoreCase(
            @Param("countryName") String countryName, Pageable pageable);
}




