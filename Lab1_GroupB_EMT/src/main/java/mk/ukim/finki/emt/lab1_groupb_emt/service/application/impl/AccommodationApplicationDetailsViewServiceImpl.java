package mk.ukim.finki.emt.lab1_groupb_emt.service.application.impl;

import mk.ukim.finki.emt.lab1_groupb_emt.model.projection.AccommodationDetailsViewProjection;
import mk.ukim.finki.emt.lab1_groupb_emt.service.application.AccommodationApplicationDetailsViewService;
import mk.ukim.finki.emt.lab1_groupb_emt.service.domain.AccommodationDetailsViewService;
import mk.ukim.finki.emt.lab1_groupb_emt.service.domain.AccommodationService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class AccommodationApplicationDetailsViewServiceImpl implements AccommodationApplicationDetailsViewService {

    private final AccommodationDetailsViewService accommodationApplicationDetailsViewService;

    public AccommodationApplicationDetailsViewServiceImpl(AccommodationDetailsViewService accommodationApplicationDetailsViewService) {
        this.accommodationApplicationDetailsViewService = accommodationApplicationDetailsViewService;
    }

    @Override
    public Page<AccommodationDetailsViewProjection> findAll(int page, int size) {
        return accommodationApplicationDetailsViewService.findAll(page,size);
    }

    @Override
    public Page<AccommodationDetailsViewProjection> searchByName(String accommodationName, int page, int size) {
        return accommodationApplicationDetailsViewService.searchByName(accommodationName,page,size);
    }

    @Override
    public Page<AccommodationDetailsViewProjection> findByCategory(String category, int page, int size) {
        return accommodationApplicationDetailsViewService.findByCategory(category,page,size);
    }

    @Override
    public Page<AccommodationDetailsViewProjection> findByHostName(String hostFullName, int page, int size) {
        return accommodationApplicationDetailsViewService.findByHostName(hostFullName, page, size);
    }

    @Override
    public Page<AccommodationDetailsViewProjection> findByCountry(String countryName, int page, int size) {
        return accommodationApplicationDetailsViewService.findByCountry(countryName, page, size);
    }
}
