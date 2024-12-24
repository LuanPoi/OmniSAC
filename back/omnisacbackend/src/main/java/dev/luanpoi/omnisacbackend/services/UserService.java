package dev.luanpoi.omnisacbackend.services;

import dev.luanpoi.omnisacbackend.dtos.UserRegistrationDto;
import dev.luanpoi.omnisacbackend.models.User;
import dev.luanpoi.omnisacbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User register(UserRegistrationDto userRegistrationDto) {
        if(!Objects.equals(userRegistrationDto.getPassword(), userRegistrationDto.getConfirmPassword())) return null;
        //Add salt and encode it
        return this.create(
            new User(
                null,
                null,
                userRegistrationDto.getFirstName(),
                userRegistrationDto.getLastName(),
                userRegistrationDto.getEmail(),
                userRegistrationDto.getPassword(),
                UUID.randomUUID().toString().substring(0, 5),
                true,
                new ArrayList<>()
            )
        );
    }

    public User create(User user) {
        return this.userRepository.save(user);
    }
}
