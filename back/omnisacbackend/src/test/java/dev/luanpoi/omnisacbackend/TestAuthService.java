package dev.luanpoi.omnisacbackend;

import dev.luanpoi.omnisacbackend.models.User;
import dev.luanpoi.omnisacbackend.services.AuthService;
import dev.luanpoi.omnisacbackend.services.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestAuthService {

    private AuthService authService;
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        authService = new AuthServiceImpl();
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    void encodePasswordSuccessfully() {
        String password = "password";
        String salt = "salt";
        String encodedPassword = authService.encodePassword(password, salt);

        assertTrue(passwordEncoder.matches(password + salt, encodedPassword));
    }

    @Test
    void validatePasswordSuccessfully() {
        User user = new User();
        String password = "password";
        String salt = "salt";
        String encodedPassword = passwordEncoder.encode(password + salt);
        user.setPassword(encodedPassword);
        user.setSalt(salt);

        assertTrue(authService.validatePassword(user, password));
    }

    @Test
    void validatePasswordFailsWithIncorrectPassword() {
        User user = new User();
        String password = "password";
        String salt = "salt";
        String encodedPassword = passwordEncoder.encode(password + salt);
        user.setPassword(encodedPassword);
        user.setSalt(salt);

        assertFalse(authService.validatePassword(user, "wrongPassword"));
    }

    @Test
    void validatePasswordFailsWithIncorrectSalt() {
        User user = new User();
        String password = "password";
        String salt = "salt";
        String encodedPassword = passwordEncoder.encode(password + salt);
        user.setPassword(encodedPassword);
        user.setSalt("wrongSalt");

        assertFalse(authService.validatePassword(user, password));
    }
}