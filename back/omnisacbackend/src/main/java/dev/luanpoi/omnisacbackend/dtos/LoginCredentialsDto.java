package dev.luanpoi.omnisacbackend.dtos;

public class LoginCredentialsDto {
    public String email;
    public String password;

    public LoginCredentialsDto() {
    }

    public LoginCredentialsDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
