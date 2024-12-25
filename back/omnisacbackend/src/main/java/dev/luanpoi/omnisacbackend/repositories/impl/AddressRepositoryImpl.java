package dev.luanpoi.omnisacbackend.repositories.impl;

import dev.luanpoi.omnisacbackend.daos.AddressDao;
import dev.luanpoi.omnisacbackend.models.Address;
import dev.luanpoi.omnisacbackend.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepositoryImpl implements AddressRepository {
    @Autowired
    AddressDao addressDao;
    @Override
    public Address save(Address address) {
        return this.addressDao.save(address);
    }
}
