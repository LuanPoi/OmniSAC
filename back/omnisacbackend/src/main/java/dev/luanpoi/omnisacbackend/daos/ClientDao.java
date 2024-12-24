package dev.luanpoi.omnisacbackend.daos;

import dev.luanpoi.omnisacbackend.models.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ClientDao extends CrudRepository<Client, UUID> {
}
