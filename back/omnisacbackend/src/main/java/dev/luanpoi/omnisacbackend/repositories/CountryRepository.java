package dev.luanpoi.omnisacbackend.repositories;

import dev.luanpoi.omnisacbackend.models.Country;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CountryRepository {
    List<Country> getCountries();

    Optional<Country> findById(UUID id);
}
