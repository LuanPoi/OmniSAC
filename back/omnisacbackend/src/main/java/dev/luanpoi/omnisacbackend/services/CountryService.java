package dev.luanpoi.omnisacbackend.services;

import dev.luanpoi.omnisacbackend.models.Country;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CountryService {
    List<Country> getCountries();

    Optional<Country> findById(UUID countryId);
}
