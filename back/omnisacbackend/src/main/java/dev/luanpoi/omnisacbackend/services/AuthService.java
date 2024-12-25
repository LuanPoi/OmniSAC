package dev.luanpoi.omnisacbackend.services;

import dev.luanpoi.omnisacbackend.models.User;
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
        return passwordEncoder.encode(password+salt);
    }

    public Boolean validatePassword(User user, String password) {
        return passwordEncoder.matches(password+user.getSalt(), user.getPassword());
    }
}
