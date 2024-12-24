package dev.luanpoi.omnisacbackend.resources;

import dev.luanpoi.omnisacbackend.dtos.ClientDto;
import dev.luanpoi.omnisacbackend.dtos.ClientRegistrationDto;
import dev.luanpoi.omnisacbackend.dtos.ViaCEPReturnVo;
import dev.luanpoi.omnisacbackend.models.Client;
import dev.luanpoi.omnisacbackend.services.ClientService;
import dev.luanpoi.omnisacbackend.services.PostalCodeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/client")
public class ClientResource {
    @Autowired
    private PostalCodeService postalCodeService;
    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<ClientDto> register(@RequestBody ClientRegistrationDto registerForm) {
        ModelMapper mapper = new ModelMapper();
        //Add form validation
        Client client = this.clientService.register(registerForm);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.map(client, ClientDto.class));
    }

    @GetMapping(value = "/validatePostalCode/{countryId}/{postalCode}")
    public ResponseEntity<ViaCEPReturnVo> validatePostalCode(
            @PathVariable("countryId") String countryId,
            @PathVariable("postalCode") String postalCode
    ){
        //add different validation by country
        ViaCEPReturnVo result = postalCodeService.validatePostalCode(postalCode);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
