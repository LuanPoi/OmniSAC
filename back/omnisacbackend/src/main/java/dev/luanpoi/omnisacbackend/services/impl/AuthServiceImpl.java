package dev.luanpoi.omnisacbackend.services.impl;

import dev.luanpoi.omnisacbackend.models.User;
import dev.luanpoi.omnisacbackend.services.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private BCryptPasswordEncoder passwordEncoder;

    public AuthServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encodePassword(String password, String salt){
        return passwordEncoder.encode(password+salt);
    }

    @Override
    public Boolean validatePassword(User user, String password) {
        return passwordEncoder.matches(password+user.getSalt(), user.getPassword());
    }
}
