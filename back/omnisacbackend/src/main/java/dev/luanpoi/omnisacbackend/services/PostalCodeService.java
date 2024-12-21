package dev.luanpoi.omnisacbackend.services;

import dev.luanpoi.omnisacbackend.dtos.ViaCEPReturnVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostalCodeService {
    private final RestTemplate restTemplate;

    public PostalCodeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ViaCEPReturnVo validatePostalCode(String postalCode){
        String url = "https://viacep.com.br/ws/"+postalCode+"/json/";
        ResponseEntity<ViaCEPReturnVo> debug = restTemplate.getForEntity(url, ViaCEPReturnVo.class);
        return debug.getBody();
    }
}
