package dev.luanpoi.omnisacbackend.services.impl;

import dev.luanpoi.omnisacbackend.models.Address;
import dev.luanpoi.omnisacbackend.repositories.AddressRepository;
import dev.luanpoi.omnisacbackend.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressServiceImpl implements AddressService {
    private final RestTemplate restTemplate;

    public AddressServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    AddressRepository addressRepository;
    @Override
    public Address create(Address address) {
        return this.addressRepository.save(address);
    }
}
