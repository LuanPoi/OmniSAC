package dev.luanpoi.omnisacbackend.resources;

import dev.luanpoi.omnisacbackend.dtos.ClientDto;
import dev.luanpoi.omnisacbackend.dtos.ClientRegisterFormDto;
import dev.luanpoi.omnisacbackend.dtos.UserDto;
import dev.luanpoi.omnisacbackend.dtos.ViaCEPReturnVo;
import dev.luanpoi.omnisacbackend.services.ClientService;
import dev.luanpoi.omnisacbackend.services.PostalCodeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/client")
public class ClientResource {
    private ModelMapper modelMapper;
    @Autowired
    private PostalCodeService postalCodeService;
    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<ClientDto> register(@RequestBody ClientRegisterFormDto registerForm) {
        ClientDto client = this.clientService.register(registerForm);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @GetMapping(value = "/validatePostalCode/{postalcode}")
    public ResponseEntity<ViaCEPReturnVo> validatePostalCode(@PathVariable("postalcode") String postalCode){
        ViaCEPReturnVo result = postalCodeService.validatePostalCode(postalCode);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
