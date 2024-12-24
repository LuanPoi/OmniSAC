package dev.luanpoi.omnisacbackend.repositories;

import dev.luanpoi.omnisacbackend.daos.AddressDao;
import dev.luanpoi.omnisacbackend.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepository {
    @Autowired
    AddressDao addressDao;
    public Address save(Address address) {
        return this.addressDao.save(address);
    }
}
