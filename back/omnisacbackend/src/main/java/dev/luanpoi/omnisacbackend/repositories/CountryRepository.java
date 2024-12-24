package dev.luanpoi.omnisacbackend.repositories;

import dev.luanpoi.omnisacbackend.daos.CountryDao;
import dev.luanpoi.omnisacbackend.dtos.CountryDto;
import dev.luanpoi.omnisacbackend.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CountryRepository {
    @Autowired
    CountryDao countryDao;
    public List<Country> getCountries() {
        return (List<Country>) countryDao.findAll();
    }

    public Optional<Country> findById(UUID id) {
        return this.countryDao.findById(id);
    }
}
