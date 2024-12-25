package dev.luanpoi.omnisacbackend.services;

import dev.luanpoi.omnisacbackend.dtos.ClientRegistrationDto;
import dev.luanpoi.omnisacbackend.models.Client;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    @Transactional
    Client register(ClientRegistrationDto form) throws Exception;

    Client create(Client client);

    Client update(Client client);

    Optional<Client> findById(UUID id);
}
