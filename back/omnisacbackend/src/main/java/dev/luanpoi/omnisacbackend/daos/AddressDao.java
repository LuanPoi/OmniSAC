package dev.luanpoi.omnisacbackend.daos;

import dev.luanpoi.omnisacbackend.models.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AddressDao extends CrudRepository<Address, UUID> {
}
