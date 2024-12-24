package dev.luanpoi.omnisacbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private BCryptPasswordEncoder passwordEncoder;

    public AuthService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String encodePassword(String password, String salt){
        return passwordEncoder.encode(password.concat(salt));
    }
}
