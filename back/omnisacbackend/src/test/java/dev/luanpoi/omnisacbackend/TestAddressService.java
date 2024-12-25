package dev.luanpoi.omnisacbackend;

import dev.luanpoi.omnisacbackend.models.Address;
import dev.luanpoi.omnisacbackend.repositories.AddressRepository;
import dev.luanpoi.omnisacbackend.services.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestAddressService {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAddressSuccessfully() {
        Address address = new Address();
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address result = addressService.create(address);

        assertNotNull(result);
        assertEquals(address, result);
    }

    @Test
    void createAddressWithNullAddress() {
        Address result = addressService.create(null);

        assertNull(result);
    }
}