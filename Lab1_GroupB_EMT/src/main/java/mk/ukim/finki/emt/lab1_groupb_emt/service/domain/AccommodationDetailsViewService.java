package mk.ukim.finki.emt.lab1_groupb_emt.service.domain;


import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.Accommodation.AccommodationDetailsViewProjection;
import org.springframework.data.domain.Page;

/**
 * Service interface for accessing accommodation details view data.
 */
public interface AccommodationDetailsViewService {

    /**
     * Get all accommodations from the view with pagination.
     *
     * @param page page number (0-based)
     * @param size page size
     * @return a page of accommodation details
     */
    Page<AccommodationDetailsViewProjection> findAll(int page, int size);

    /**
     * Search accommodations by name with pagination.
     *
     * @param accommodationName the name to search for
     * @param page page number (0-based)
     * @param size page size
     * @return a page of matching accommodation details
     */
    Page<AccommodationDetailsViewProjection> searchByName(String accommodationName, int page, int size);

    /**
     * Find accommodations by category with pagination.
     *
     * @param category the category filter
     * @param page page number (0-based)
     * @param size page size
     * @return a page of accommodations in the specified category
     */
    Page<AccommodationDetailsViewProjection> findByCategory(String category, int page, int size);

    /**
     * Find accommodations by host name with pagination.
     *
     * @param hostFullName the host name to search for
     * @param page page number (0-based)
     * @param size page size
     * @return a page of accommodations by the specified host
     */
    Page<AccommodationDetailsViewProjection> findByHostName(String hostFullName, int page, int size);

    /**
     * Find accommodations by country with pagination.
     *
     * @param countryName the country name filter
     * @param page page number (0-based)
     * @param size page size
     * @return a page of accommodations in the specified country
     */
    Page<AccommodationDetailsViewProjection> findByCountry(String countryName, int page, int size);
}
