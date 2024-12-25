package dev.luanpoi.omnisacbackend.services.impl;

import dev.luanpoi.omnisacbackend.dtos.ClientRegistrationDto;
import dev.luanpoi.omnisacbackend.dtos.UserRegistrationFormDto;
import dev.luanpoi.omnisacbackend.dtos.ViaCEPReturn;
import dev.luanpoi.omnisacbackend.models.Address;
import dev.luanpoi.omnisacbackend.models.Client;
import dev.luanpoi.omnisacbackend.models.Country;
import dev.luanpoi.omnisacbackend.models.User;
import dev.luanpoi.omnisacbackend.repositories.ClientRepository;
import dev.luanpoi.omnisacbackend.services.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;
    @Autowired
    CountryService countryService;
    @Autowired
    PostalCodeService postalCodeService;

    @Override
    @Transactional
    public Client register(ClientRegistrationDto form) throws Exception {
        ViaCEPReturn postalCodeResult = this.postalCodeService.validatePostalCode(form.getPostalCode());
        if(postalCodeResult.getEstado() != null && !postalCodeResult.getEstado().isBlank()) form.setState(postalCodeResult.getEstado());
        if(postalCodeResult.getLocalidade() != null && !postalCodeResult.getLocalidade().isBlank()) form.setCity(postalCodeResult.getLocalidade());
        if(postalCodeResult.getLogradouro() != null && !postalCodeResult.getLogradouro().isBlank()) form.setStreet(postalCodeResult.getLogradouro());
        if(postalCodeResult.getUnidade() != null && !postalCodeResult.getUnidade().isBlank()) form.setNumber(postalCodeResult.getUnidade());
        if(postalCodeResult.getComplemento() != null && !postalCodeResult.getComplemento().isBlank()) form.setComplement(postalCodeResult.getComplemento());

        User user = this.userService.register(new UserRegistrationFormDto(form.getFirstName(), form.getLastName(), form.getEmail(), form.getPassword(), form.getConfirmPassword()));

        Client client = this.create(new Client(null, user, null, null, null, new ArrayList<>(), new ArrayList<>()));

        Optional<Country> country = this.countryService.findById(form.getCountryId());
        if(country.isEmpty()) return null;

        Address address = this.addressService.create(new Address(null, client, country.get(), form.getPostalCode(), form.getStreet(), form.getNumber(), form.getComplement(), form.getNeighborhood(), form.getCity(), form.getState()));
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);

        client.setAddresses(addresses);

        return this.update(client);
    }

    @Override
    public Client create(Client client) {
        return this.clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        Client newClient = new Client();
        Optional<Client> existingClient = this.findById(client.getId());
        if(existingClient.isEmpty()){
            return null;
        }
        BeanUtils.copyProperties(existingClient.get(), newClient);
        BeanUtils.copyProperties(client, newClient);
        return this.clientRepository.save(newClient);
    }

    @Override
    public Optional<Client> findById(UUID id) {
        return this.clientRepository.findById(id);
    }
}
