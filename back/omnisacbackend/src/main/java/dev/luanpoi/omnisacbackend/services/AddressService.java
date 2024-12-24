package dev.luanpoi.omnisacbackend.services;

import dev.luanpoi.omnisacbackend.models.Address;
import dev.luanpoi.omnisacbackend.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;
    public Address create(Address address) {
        return this.addressRepository.save(address);
    }
}
