package dev.luanpoi.omnisacbackend.repositories.impl;

import dev.luanpoi.omnisacbackend.daos.CountryDao;
import dev.luanpoi.omnisacbackend.models.Country;
import dev.luanpoi.omnisacbackend.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CountryRepositoryImpl implements CountryRepository {
    @Autowired
    CountryDao countryDao;
    @Override
    public List<Country> getCountries() {
        return (List<Country>) countryDao.findAll();
    }

    @Override
    public Optional<Country> findById(UUID id) {
        return this.countryDao.findById(id);
    }
}
