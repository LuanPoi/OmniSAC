package dev.luanpoi.omnisacbackend;

import dev.luanpoi.omnisacbackend.dtos.UserRegistrationFormDto;
import dev.luanpoi.omnisacbackend.models.User;
import dev.luanpoi.omnisacbackend.repositories.UserRepository;
import dev.luanpoi.omnisacbackend.services.AuthService;
import dev.luanpoi.omnisacbackend.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestUserService {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUserSuccessfully() {
        UserRegistrationFormDto dto = new UserRegistrationFormDto();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setEmail("john.doe@example.com");
        dto.setPassword("password");
        dto.setConfirmPassword("password");

        when(authService.encodePassword(any(String.class), any(String.class))).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(new User());

        User user = userService.register(dto);

        assertNotNull(user);
    }

    @Test
    void registerUserPasswordMismatch() {
        UserRegistrationFormDto dto = new UserRegistrationFormDto();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setEmail("john.doe@example.com");
        dto.setPassword("password");
        dto.setConfirmPassword("differentPassword");

        User user = userService.register(dto);

        assertNull(user);
    }

    @Test
    void createUserSuccessfully() {
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.create(user);

        assertNotNull(createdUser);
    }

    @Test
    void findByEmailSuccessfully() {
        User user = new User();
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findByEmail("john.doe@example.com");

        assertTrue(foundUser.isPresent());
    }

    @Test
    void findByEmailNotFound() {
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.empty());

        Optional<User> foundUser = userService.findByEmail("john.doe@example.com");

        assertFalse(foundUser.isPresent());
    }

    @Test
    void checkIfCredentialsAreValidSuccessfully() {
        User user = new User();
        when(authService.validatePassword(user, "password")).thenReturn(true);

        boolean isValid = userService.checkIfCredentialsAreValid(user, "password");

        assertTrue(isValid);
    }

    @Test
    void checkIfCredentialsAreInvalid() {
        User user = new User();
        when(authService.validatePassword(user, "password")).thenReturn(false);

        boolean isValid = userService.checkIfCredentialsAreValid(user, "password");

        assertFalse(isValid);
    }
}