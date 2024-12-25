package dev.luanpoi.omnisacbackend.repositories;

import dev.luanpoi.omnisacbackend.models.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {
    Client save(Client client);

    Optional<Client> findById(UUID id);
}
