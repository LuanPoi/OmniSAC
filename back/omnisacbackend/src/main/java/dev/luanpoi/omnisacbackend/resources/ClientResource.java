package dev.luanpoi.omnisacbackend.resources;

import dev.luanpoi.omnisacbackend.dtos.ClientDto;
import dev.luanpoi.omnisacbackend.dtos.ClientRegistrationDto;
import dev.luanpoi.omnisacbackend.dtos.ResponseDto;
import dev.luanpoi.omnisacbackend.dtos.ViaCEPReturn;
import dev.luanpoi.omnisacbackend.models.Client;
import dev.luanpoi.omnisacbackend.services.AddressService;
import dev.luanpoi.omnisacbackend.services.ClientService;
import dev.luanpoi.omnisacbackend.services.PostalCodeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/clients")
public class ClientResource {
    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<ResponseDto<ClientDto, String>> register(@RequestBody ClientRegistrationDto registerForm) {
        ModelMapper mapper = new ModelMapper();

        ArrayList<String> errors = registerForm.validate();
        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseDto<ClientDto, String>(
                            null,
                            false,
                            errors
                    )
            );
        }

        try {
            Client client = this.clientService.register(registerForm);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto<ClientDto, String>(
                            mapper.map(client, ClientDto.class),
                            true,
                            new ArrayList<>()
                    ));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto<ClientDto, String>(null, false, Arrays.asList(e.getMessage())));
        }
    }


}
