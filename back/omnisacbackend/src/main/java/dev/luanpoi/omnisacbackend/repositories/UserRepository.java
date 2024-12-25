package dev.luanpoi.omnisacbackend.repositories;

import dev.luanpoi.omnisacbackend.models.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findByEmail(String email);
}
