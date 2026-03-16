package mk.ukim.finki.emt.lab1_groupb_emt.service.domain;

import mk.ukim.finki.emt.lab1_groupb_emt.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Optional<Country> findById(Long id);

    List<Country> findAll();

    Country create(Country product);

    Optional<Country> update(Long id, Country product);

    Optional<Country> deleteById(Long id);
}
