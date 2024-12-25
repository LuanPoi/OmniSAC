package dev.luanpoi.omnisacbackend.services;

import dev.luanpoi.omnisacbackend.dtos.UserRegistrationFormDto;
import dev.luanpoi.omnisacbackend.models.User;

import java.util.Optional;

public interface UserService {
    User register(UserRegistrationFormDto userRegistrationDto);

    User create(User user);

    Optional<User> findByEmail(String email);

    boolean checkIfCredentialsAreValid(User user, String password);
}
