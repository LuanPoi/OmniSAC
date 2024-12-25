package dev.luanpoi.omnisacbackend.resources;

import dev.luanpoi.omnisacbackend.dtos.*;
import dev.luanpoi.omnisacbackend.models.Client;
import dev.luanpoi.omnisacbackend.services.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/clients")
public class ClientResource {
    private static final Logger LOGGER = LogManager.getLogger();
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
            LOGGER.error("(ClientResource) /register"+" -> exception: "+e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto<ClientDto, String>(null, false, Arrays.asList(e.getMessage())));
        }
    }
}
