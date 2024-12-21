package dev.luanpoi.omnisacbackend.dtos;

import dev.luanpoi.omnisacbackend.models.PhoneNumber;

import java.util.UUID;

public class UserDto {
    private UUID id;
    private PhoneNumberDto defaultPhoneNumber;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;

    public UserDto() {
    }

    public UserDto(UUID id, PhoneNumberDto defaultPhoneNumber, String firstName, String lastName, String email, boolean isActive) {
        this.id = id;
        this.defaultPhoneNumber = defaultPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isActive = isActive;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PhoneNumberDto getDefaultPhoneNumber() {
        return defaultPhoneNumber;
    }

    public void setDefaultPhoneNumber(PhoneNumberDto defaultPhoneNumber) {
        this.defaultPhoneNumber = defaultPhoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
