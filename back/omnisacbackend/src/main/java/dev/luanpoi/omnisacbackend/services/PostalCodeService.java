package dev.luanpoi.omnisacbackend.services;

import dev.luanpoi.omnisacbackend.dtos.ViaCEPReturn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class PostalCodeService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final RestTemplate restTemplate;

    public PostalCodeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ViaCEPReturn validatePostalCode(String postalCode) throws Exception{
        try {
            String url = "https://viacep.com.br/ws/" + postalCode + "/json/";
            ResponseEntity<ViaCEPReturn> postalCodeServiceReturn = restTemplate.getForEntity(url, ViaCEPReturn.class);
            return postalCodeServiceReturn.getBody();
        } catch (ResourceAccessException e) {
            LOGGER.error("(ClientResource) /register"+" -> exception: "+e);
            throw new ResourceAccessException("ERROR.CONNECTION.POSTAL_CODE_SERVICE");
        }
    }
}
