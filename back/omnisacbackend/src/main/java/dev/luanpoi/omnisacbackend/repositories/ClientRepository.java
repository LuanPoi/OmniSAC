package dev.luanpoi.omnisacbackend.repositories;

import dev.luanpoi.omnisacbackend.daos.ClientDao;
import dev.luanpoi.omnisacbackend.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ClientRepository {
    @Autowired
    ClientDao clientDao;

    public Client save(Client client) {
        return this.clientDao.save(client);
    }

    public Optional<Client> findById(UUID id) {
        return this.clientDao.findById(id);
    }
}
