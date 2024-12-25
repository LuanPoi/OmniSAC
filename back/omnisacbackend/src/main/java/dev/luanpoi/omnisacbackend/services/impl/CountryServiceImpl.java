package dev.luanpoi.omnisacbackend.services.impl;

import dev.luanpoi.omnisacbackend.models.Country;
import dev.luanpoi.omnisacbackend.repositories.CountryRepository;
import dev.luanpoi.omnisacbackend.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<Country> getCountries() {
        return this.countryRepository.getCountries();
    }

    @Override
    public Optional<Country> findById(UUID countryId) {
        return this.countryRepository.findById(countryId);
    }
}
