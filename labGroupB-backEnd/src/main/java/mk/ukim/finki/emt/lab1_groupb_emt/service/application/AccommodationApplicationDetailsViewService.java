package mk.ukim.finki.emt.lab1_groupb_emt.service.application;

import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.Accommodation.AccommodationDetailsViewProjection;
import org.springframework.data.domain.Page;

public interface AccommodationApplicationDetailsViewService {
    Page<AccommodationDetailsViewProjection> findAll(int page, int size);

    Page<AccommodationDetailsViewProjection> searchByName(String accommodationName, int page, int size);

    Page<AccommodationDetailsViewProjection> findByCategory(String category, int page, int size);

    Page<AccommodationDetailsViewProjection> findByHostName(String hostFullName, int page, int size);

    Page<AccommodationDetailsViewProjection> findByCountry(String countryName, int page, int size);
}
