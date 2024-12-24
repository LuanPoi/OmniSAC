package dev.luanpoi.omnisacbackend.services;

import dev.luanpoi.omnisacbackend.models.Country;
import dev.luanpoi.omnisacbackend.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CountryService {
    @Autowired
    CountryRepository countryRepository;

    public List<Country> getCountries() {
        return this.countryRepository.getCountries();
    }

    public Optional<Country> findById(UUID countryId) {
        return this.countryRepository.findById(countryId);
    }
}
