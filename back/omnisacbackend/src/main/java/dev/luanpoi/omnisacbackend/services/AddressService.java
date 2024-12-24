package dev.luanpoi.omnisacbackend.services;

import dev.luanpoi.omnisacbackend.models.Address;
import dev.luanpoi.omnisacbackend.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressService {
    private final RestTemplate restTemplate;

    public AddressService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    AddressRepository addressRepository;
    public Address create(Address address) {
        return this.addressRepository.save(address);
    }
}
