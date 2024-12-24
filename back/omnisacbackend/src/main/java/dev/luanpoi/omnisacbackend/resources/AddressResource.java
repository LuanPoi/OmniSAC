package dev.luanpoi.omnisacbackend.resources;

import dev.luanpoi.omnisacbackend.dtos.ClientDto;
import dev.luanpoi.omnisacbackend.dtos.ResponseDto;
import dev.luanpoi.omnisacbackend.dtos.ViaCEPReturn;
import dev.luanpoi.omnisacbackend.services.PostalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/addresses")
public class AddressResource {
    @Autowired
    private PostalCodeService postalCodeService;

    @GetMapping(value = "/validatePostalCode/{countryId}/{postalCode}")
    public ResponseEntity<ResponseDto<ViaCEPReturn, String>> validatePostalCode(
            @PathVariable("countryId") String countryId,
            @PathVariable("postalCode") String postalCode
    ){
        //add different validation by country
        try {
            ViaCEPReturn result = postalCodeService.validatePostalCode(postalCode);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<ViaCEPReturn, String>(result, true, new ArrayList<>()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto<ViaCEPReturn, String>(null, false, Arrays.asList(e.getMessage())));
        }
    }
}
