package dev.luanpoi.omnisacbackend.daos;

import dev.luanpoi.omnisacbackend.models.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CountryDao extends CrudRepository<Country, UUID> {
}
