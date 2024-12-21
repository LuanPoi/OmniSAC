package dev.luanpoi.omnisacbackend.services;

import dev.luanpoi.omnisacbackend.dtos.ClientDto;
import dev.luanpoi.omnisacbackend.dtos.ClientRegisterFormDto;
import dev.luanpoi.omnisacbackend.dtos.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class ClientService {
    public ClientDto register(ClientRegisterFormDto registerForm){
        return new ClientDto(UUID.randomUUID(), new UserDto(UUID.randomUUID(), null, registerForm.getFirstName(), registerForm.getLastName(), registerForm.getEmail(), true), null, null, null, null, new ArrayList<>());
    }
}
