package mk.ukim.finki.emt.lab1_groupb_emt.service.domain.impl;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Accomodation;
import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Review;
import mk.ukim.finki.emt.lab1_groupb_emt.repository.AccomodationRepository;
import mk.ukim.finki.emt.lab1_groupb_emt.repository.ReviewRepository;
import mk.ukim.finki.emt.lab1_groupb_emt.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccomodationRepository accomodationRepository;
    private final ReviewRepository reviewRepository;

    public AccommodationServiceImpl(AccomodationRepository accomodationRepository, ReviewRepository reviewRepository) {
        this.accomodationRepository = accomodationRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<Accomodation> findById(Long id) {
        return accomodationRepository.findById(id);
    }

    @Override
    public List<Accomodation> findAll() {
        return accomodationRepository.findAll();
    }

    @Override
    public Accomodation create(Accomodation accommodation) {
        return accomodationRepository.save(accommodation);
    }

    @Override
    public Optional<Accomodation> update(Long id, Accomodation accommodation) {

        return accomodationRepository
                .findById(id)
                .map((existingAccommodation) -> {
                    existingAccommodation.setName(accommodation.getName());
                    existingAccommodation.setCategory(accommodation.getCategory());
                    existingAccommodation.setHost(accommodation.getHost());
                    existingAccommodation.setNumRooms(accommodation.getNumRooms());
                    existingAccommodation.setCondition(accommodation.getCondition());
                    existingAccommodation.setOccupied(accommodation.getOccupied());
                    return accomodationRepository.save(existingAccommodation);
                });
    }

    @Override
    public Optional<Accomodation> deleteById(Long id) {
        Optional<Accomodation> accommodation = accomodationRepository.findById(id);
        accommodation.ifPresent(accomodationRepository::delete);
        return accommodation;
    }

    @Override
    public void addReview(Long accommodationId, String comment, Double rating) {
        Review review = new Review(comment, rating);
        review.setAccomodation(accomodationRepository.findById(accommodationId).orElseThrow(() -> new RuntimeException("Accommodation not found")));

        accomodationRepository.findById(accommodationId).ifPresent(accommodation -> {
            accommodation.getReviews().add(review);
            accomodationRepository.save(accommodation);
        });
    }
}
