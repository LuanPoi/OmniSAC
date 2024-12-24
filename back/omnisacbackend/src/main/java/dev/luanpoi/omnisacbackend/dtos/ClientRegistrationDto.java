package dev.luanpoi.omnisacbackend.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String postalCode;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private UUID countryId;

    public ClientRegistrationDto() {
    }

    public ClientRegistrationDto(String firstName, String lastName, String email, String password, String confirmPassword, String postalCode, String street, String number, String complement, String neighborhood, String city, String state, UUID countryId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.postalCode = postalCode;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.countryId = countryId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UUID getCountryId() {
        return countryId;
    }

    public void setCountryId(UUID countryId) {
        this.countryId = countryId;
    }

    public ArrayList<String> validate(){
        ArrayList<String> errors = new ArrayList<String>();
        if(!Objects.equals(this.password, this.confirmPassword)) errors.add("ERROR.CONFIRM_PASSWORD.DONT_MATCH");
        if(this.firstName.isEmpty()) errors.add("ERROR.FIRST_NAME.IS_EMPTY");
        if(this.lastName.isEmpty()) errors.add("ERROR.LAST_NAME.IS_EMPTY");
        if(this.email.isEmpty()) errors.add("ERROR.EMAIL.IS_EMPTY");
        if(!Pattern.compile("^(.+)@(\\S+)$").matcher(this.email).matches()) errors.add("ERROR.EMAIL.INVALID");
        String cleanedPostalCode = postalCode.trim().replaceAll("\\D", "");
        if(cleanedPostalCode.isEmpty()) errors.add("ERROR.POSTAL_CODE.IS_EMPTY");
        if(cleanedPostalCode.length() != 8) errors.add("ERROR.POSTAL_CODE.IS_INVALID"); // specific for CEP
        return errors;
    }
}
