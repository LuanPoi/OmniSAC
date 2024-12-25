package dev.luanpoi.omnisacbackend;

import dev.luanpoi.omnisacbackend.dtos.ViaCEPReturn;
import dev.luanpoi.omnisacbackend.services.PostalCodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestPostalCodeService {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PostalCodeService postalCodeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void validatePostalCodeSuccessfully() throws Exception {
//        ViaCEPReturn viaCEPReturn = new ViaCEPReturn();
//        when(restTemplate.getForEntity(anyString(), eq(ViaCEPReturn.class))).thenReturn(new ResponseEntity<>(viaCEPReturn, HttpStatus.OK));
//
//        ViaCEPReturn result = postalCodeService.validatePostalCode("12345678");
//
//        assertNotNull(result);
//    }

//    @Test
//    void validatePostalCodeThrowsResourceAccessException() {
//        when(restTemplate.getForEntity(anyString(), eq(ViaCEPReturn.class))).thenThrow(new ResourceAccessException("ERROR.CONNECTION.POSTAL_CODE_SERVICE"));
//
//        Exception exception = assertThrows(ResourceAccessException.class, () -> {
//            postalCodeService.validatePostalCode("12345678");
//        });
//
//        assertEquals("ERROR.CONNECTION.POSTAL_CODE_SERVICE", exception.getMessage());
//    }
}