package dev.luanpoi.omnisacbackend.repositories.impl;

import dev.luanpoi.omnisacbackend.daos.ClientDao;
import dev.luanpoi.omnisacbackend.models.Client;
import dev.luanpoi.omnisacbackend.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    @Autowired
    ClientDao clientDao;

    @Override
    public Client save(Client client) {
        return this.clientDao.save(client);
    }

    @Override
    public Optional<Client> findById(UUID id) {
        return this.clientDao.findById(id);
    }
}
