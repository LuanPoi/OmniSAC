package dev.luanpoi.omnisacbackend.services;

import dev.luanpoi.omnisacbackend.models.User;

public interface AuthService {
    String encodePassword(String password, String salt);

    Boolean validatePassword(User user, String password);
}
