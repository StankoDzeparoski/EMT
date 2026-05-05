package mk.ukim.finki.emt.lab1_groupb_emt.service.domain.impl;

import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.Accommodation.AccommodationDetailsViewProjection;
import mk.ukim.finki.emt.lab1_groupb_emt.repository.AccommodationDetailsViewRepository;
import mk.ukim.finki.emt.lab1_groupb_emt.service.domain.AccommodationDetailsViewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implementation of AccommodationDetailsViewService.
 * Provides business logic for accessing and querying accommodation details view data.
 */
@Service
public class AccommodationDetailsViewServiceImpl implements AccommodationDetailsViewService {
    
    private final AccommodationDetailsViewRepository repository;
    
    public AccommodationDetailsViewServiceImpl(AccommodationDetailsViewRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public Page<AccommodationDetailsViewProjection> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAllProjection(pageable);
    }
    
    @Override
    public Page<AccommodationDetailsViewProjection> searchByName(String accommodationName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findByAccommodationNameContainingIgnoreCase(accommodationName, pageable);
    }
    
    @Override
    public Page<AccommodationDetailsViewProjection> findByCategory(String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findByCategory(category, pageable);
    }
    
    @Override
    public Page<AccommodationDetailsViewProjection> findByHostName(String hostFullName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findByHostFullNameContainingIgnoreCase(hostFullName, pageable);
    }
    
    @Override
    public Page<AccommodationDetailsViewProjection> findByCountry(String countryName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findByCountryNameContainingIgnoreCase(countryName, pageable);
    }
}


