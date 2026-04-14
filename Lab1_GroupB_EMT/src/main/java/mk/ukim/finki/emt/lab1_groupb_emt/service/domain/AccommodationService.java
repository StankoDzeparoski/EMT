package mk.ukim.finki.emt.lab1_groupb_emt.service.domain;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Accomodation;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Review;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Category;
import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.AccommodationExtendedProjection;
import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.AccommodationProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    Optional<Accomodation> findById(Long id);

    List<Accomodation> findAll();

    Accomodation create(Accomodation product);

    Optional<Accomodation> update(Long id, Accomodation product);

    Optional<Accomodation> deleteById(Long id);

    void addReview(Long accommodationId, String comment, Double rating);

    Page<Accomodation> findAll(int page, int size, String sortBy);

    Page<Accomodation> findAllWithFilters(
            int page,
            int size,
            String sortBy,
            Category category,
            Long hostId,
            Long countryId,
            Integer numRooms,
            Boolean hasAvailableRooms);

    Page<AccommodationExtendedProjection> findAllExtendedProjection(int page, int size);

    Page<AccommodationProjection> findAllProjection(int page, int size);

    List<Accomodation> findAllWithHost();

    List<Accomodation> findAllWithHostAndCountry();
    
    /**
     * Rent an accommodation by reducing the number of available rooms.
     * When all rooms are occupied, the accommodation is marked as occupied.
     * Publishes an AccommodationRentedEvent upon successful rental.
     *
     * @param accommodationId the accommodation ID to rent
     * @param numberOfRoomsToRent the number of rooms to rent (default 1)
     * @return the updated accommodation
     * @throws IllegalArgumentException if accommodation not found or no rooms available
     */
    Optional<Accomodation> rentAccommodation(Long accommodationId, Integer numberOfRoomsToRent);
    
    /**
     * Rent an accommodation with a single room.
     * Publishes an AccommodationRentedEvent upon successful rental.
     *
     * @param accommodationId the accommodation ID to rent
     * @return the updated accommodation
     * @throws IllegalArgumentException if accommodation not found or no rooms available
     */
    Optional<Accomodation> rentAccommodation(Long accommodationId);
}
