package mk.ukim.finki.emt.lab1_groupb_emt.service.domain;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Accomodation;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Review;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    Optional<Accomodation> findById(Long id);

    List<Accomodation> findAll();

    Accomodation create(Accomodation product);

    Optional<Accomodation> update(Long id, Accomodation product);

    Optional<Accomodation> deleteById(Long id);

    void addReview(Long accommodationId, String comment, Double rating);
}
