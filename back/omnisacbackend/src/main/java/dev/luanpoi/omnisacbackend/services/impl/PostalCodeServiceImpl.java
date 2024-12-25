package dev.luanpoi.omnisacbackend.services.impl;

import dev.luanpoi.omnisacbackend.dtos.ViaCEPReturn;
import dev.luanpoi.omnisacbackend.services.PostalCodeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class PostalCodeServiceImpl implements PostalCodeService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final RestTemplate restTemplate;

    public PostalCodeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
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
