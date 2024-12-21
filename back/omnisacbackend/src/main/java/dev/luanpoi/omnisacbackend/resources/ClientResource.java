package dev.luanpoi.omnisacbackend.resources;

import dev.luanpoi.omnisacbackend.dtos.ClientDto;
import dev.luanpoi.omnisacbackend.dtos.ClientRegisterFormDto;
import dev.luanpoi.omnisacbackend.dtos.UserDto;
import dev.luanpoi.omnisacbackend.dtos.ViaCEPReturnVo;
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

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<ClientDto> findById(@RequestBody ClientRegisterFormDto registerForm) {
        return ResponseEntity.status(HttpStatus.OK).body(new ClientDto(UUID.randomUUID(), new UserDto(UUID.randomUUID(), null, registerForm.getFirstName(), registerForm.getLastName(), registerForm.getEmail(), true), null, null, null, null, new ArrayList<>()));
    }

    @GetMapping(value = "/validatePostalCode/{postalcode}")
    public ResponseEntity<ViaCEPReturnVo> validatePostalCode(@PathVariable("postalcode") String postalCode){
        ViaCEPReturnVo result = postalCodeService.validatePostalCode(postalCode);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
