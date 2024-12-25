package dev.luanpoi.omnisacbackend.resources;


import dev.luanpoi.omnisacbackend.dtos.LoginCredentialsDto;
import dev.luanpoi.omnisacbackend.dtos.ResponseDto;
import dev.luanpoi.omnisacbackend.models.Client;
import dev.luanpoi.omnisacbackend.models.User;
import dev.luanpoi.omnisacbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/users")
public class UserResource {
    @Autowired
    UserService userService;

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<ResponseDto<String, String>> login(@RequestBody LoginCredentialsDto credentials) {
        if(credentials.email.isBlank() || credentials.password.isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto<>(null, false, Arrays.asList("Campos email e senha não podem estar vazios.")));
        }
        Optional<User> user = userService.findByEmail(credentials.email);
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto<>(null, false, Arrays.asList("Email não existe no sistema")));
        }
        if(!userService.checkIfCredentialsAreValid(user.get(), credentials.password)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDto<>(null, false, Arrays.asList("Senha inválida")));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("Credenciais válidas", true, new ArrayList<>()));
    }
}
