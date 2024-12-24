package dev.luanpoi.omnisacbackend.services;

import dev.luanpoi.omnisacbackend.dtos.UserRegistrationFormDto;
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
    @Autowired
    AuthService authService;

    public User register(UserRegistrationFormDto userRegistrationDto) {
        if(!Objects.equals(userRegistrationDto.getPassword(), userRegistrationDto.getConfirmPassword())) return null;

        String salt = UUID.randomUUID().toString().substring(0, 5);
        String encodedPassword = this.authService.encodePassword(userRegistrationDto.getPassword(), salt);
        userRegistrationDto.setPassword(encodedPassword);

        return this.create(
            new User(
                null,
                null,
                userRegistrationDto.getFirstName(),
                userRegistrationDto.getLastName(),
                userRegistrationDto.getEmail(),
                userRegistrationDto.getPassword(),
                salt,
                true,
                new ArrayList<>()
            )
        );
    }

    public User create(User user) {
        return this.userRepository.save(user);
    }
}
