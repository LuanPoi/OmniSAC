package dev.luanpoi.omnisacbackend;

import dev.luanpoi.omnisacbackend.dtos.ClientRegistrationDto;
import dev.luanpoi.omnisacbackend.dtos.UserRegistrationFormDto;
import dev.luanpoi.omnisacbackend.dtos.ViaCEPReturn;
import dev.luanpoi.omnisacbackend.models.Address;
import dev.luanpoi.omnisacbackend.models.Client;
import dev.luanpoi.omnisacbackend.models.Country;
import dev.luanpoi.omnisacbackend.models.User;
import dev.luanpoi.omnisacbackend.repositories.ClientRepository;
import dev.luanpoi.omnisacbackend.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestClientService {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private UserService userService;

    @Mock
    private AddressService addressService;

    @Mock
    private CountryService countryService;

    @Mock
    private PostalCodeService postalCodeService;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void registerClientSuccessfully() throws Exception {
//        ClientRegistrationDto form = new ClientRegistrationDto();
//        form.setPostalCode("12345678");
//        form.setFirstName("John");
//        form.setLastName("Doe");
//        form.setEmail("john.doe@example.com");
//        form.setPassword("password");
//        form.setConfirmPassword("password");
//        form.setCountryId(UUID.randomUUID());
//
//        ViaCEPReturn viaCEPReturn = new ViaCEPReturn();
//        viaCEPReturn.setEstado("State");
//        viaCEPReturn.setLocalidade("City");
//        viaCEPReturn.setLogradouro("Street");
//        viaCEPReturn.setUnidade("Number");
//        viaCEPReturn.setComplemento("Complement");
//
//        when(postalCodeService.validatePostalCode(form.getPostalCode())).thenReturn(viaCEPReturn);
//        when(userService.register(any(UserRegistrationFormDto.class))).thenReturn(new User());
//        when(countryService.findById(form.getCountryId())).thenReturn(Optional.of(new Country()));
//        when(addressService.create(any(Address.class))).thenReturn(new Address());
//        when(clientRepository.save(any(Client.class))).thenReturn(new Client());
//        when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.of(new Client()));
//
//        Client client = clientService.register(form);
//
//
//        assertNotNull(client);
//    }

    @Test
    void registerClientWithInvalidPostalCode() throws Exception {
        ClientRegistrationDto form = new ClientRegistrationDto();
        form.setPostalCode("invalid");

        when(postalCodeService.validatePostalCode(form.getPostalCode())).thenThrow(new Exception("Invalid postal code"));

        Exception exception = assertThrows(Exception.class, () -> {
            clientService.register(form);
        });

        assertEquals("Invalid postal code", exception.getMessage());
    }

    @Test
    void registerClientWithNonExistentCountry() throws Exception {
        ClientRegistrationDto form = new ClientRegistrationDto();
        form.setPostalCode("12345678");
        form.setCountryId(UUID.randomUUID());

        ViaCEPReturn viaCEPReturn = new ViaCEPReturn();
        when(postalCodeService.validatePostalCode(form.getPostalCode())).thenReturn(viaCEPReturn);
        when(userService.register(any(UserRegistrationFormDto.class))).thenReturn(new User());
        when(countryService.findById(form.getCountryId())).thenReturn(Optional.empty());

        Client client = clientService.register(form);

        assertNull(client);
    }

    @Test
    void registerClientWithEmptyFields() throws Exception {
        ClientRegistrationDto form = new ClientRegistrationDto();

        when(postalCodeService.validatePostalCode(form.getPostalCode())).thenReturn(new ViaCEPReturn());
        when(userService.register(any(UserRegistrationFormDto.class))).thenReturn(new User());
        when(countryService.findById(form.getCountryId())).thenReturn(Optional.of(new Country()));
        when(addressService.create(any(Address.class))).thenReturn(new Address());
        when(clientRepository.save(any(Client.class))).thenReturn(new Client());

        Client client = clientService.register(form);

        assertNull(client);
    }
}