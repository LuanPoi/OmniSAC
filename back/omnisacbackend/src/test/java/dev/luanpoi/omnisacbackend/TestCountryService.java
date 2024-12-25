package dev.luanpoi.omnisacbackend;

import dev.luanpoi.omnisacbackend.models.Country;
import dev.luanpoi.omnisacbackend.repositories.CountryRepository;
import dev.luanpoi.omnisacbackend.services.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestCountryService {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCountriesReturnsListOfCountries() {
        List<Country> countries = List.of(new Country(), new Country());
        when(countryRepository.getCountries()).thenReturn(countries);

        List<Country> result = countryService.getCountries();

        assertEquals(2, result.size());
    }

    @Test
    void findByIdReturnsCountryWhenFound() {
        UUID countryId = UUID.randomUUID();
        Country country = new Country();
        when(countryRepository.findById(countryId)).thenReturn(Optional.of(country));

        Optional<Country> result = countryService.findById(countryId);

        assertTrue(result.isPresent());
        assertEquals(country, result.get());
    }

    @Test
    void findByIdReturnsEmptyWhenNotFound() {
        UUID countryId = UUID.randomUUID();
        when(countryRepository.findById(countryId)).thenReturn(Optional.empty());

        Optional<Country> result = countryService.findById(countryId);

        assertFalse(result.isPresent());
    }
}