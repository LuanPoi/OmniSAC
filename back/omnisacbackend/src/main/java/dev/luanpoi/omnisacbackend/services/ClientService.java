package dev.luanpoi.omnisacbackend.services;

import dev.luanpoi.omnisacbackend.dtos.ClientRegistrationDto;
import dev.luanpoi.omnisacbackend.dtos.UserRegistrationDto;
import dev.luanpoi.omnisacbackend.models.Address;
import dev.luanpoi.omnisacbackend.models.Client;
import dev.luanpoi.omnisacbackend.models.Country;
import dev.luanpoi.omnisacbackend.models.User;
import dev.luanpoi.omnisacbackend.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;
    @Autowired
    CountryService countryService;

    @Transactional
    public Client register(ClientRegistrationDto form) {
        User user = this.userService.register(new UserRegistrationDto(form.getFirstName(), form.getLastName(), form.getEmail(), form.getPassword(), form.getConfirmPassword()));
        Client client = this.create(new Client(null, user, null, null, null, new ArrayList<>(), new ArrayList<>()));
        Optional<Country> country = this.countryService.findById(form.getCountryId());
        if(country.isEmpty()) return null;
        Address address = this.addressService.create(new Address(null, client, country.get(), form.getPostalCode(), form.getStreet(), form.getNumber(), form.getComplement(), form.getNeighborhood(), form.getCity(), form.getState()));
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        client.setAddresses(addresses);
        return this.update(client);
    }

    private Client create(Client client) {
        return this.clientRepository.save(client);
    }

    private Client update(Client client) {
        Client newClient = new Client();
        Optional<Client> existingClient = this.findById(client.getId());
        if(existingClient.isEmpty()){
            return null;
        }
        BeanUtils.copyProperties(existingClient.get(), newClient);
        BeanUtils.copyProperties(client, newClient);
        return this.clientRepository.save(newClient);
    }

    private Optional<Client> findById(UUID id) {
        return this.clientRepository.findById(id);
    }
}
