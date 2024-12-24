package dev.luanpoi.omnisacbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.luanpoi.omnisacbackend.models.Country;
import dev.luanpoi.omnisacbackend.models.User;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneNumberDto {
    private UUID id;
    @JsonIgnore()
    private User user;
    private CountryDto country;
    private String areaCode;
    private String number;

    public PhoneNumberDto() {
    }

    public PhoneNumberDto(UUID id, User user, CountryDto country, String areaCode, String number) {
        this.id = id;
        this.user = user;
        this.country = country;
        this.areaCode = areaCode;
        this.number = number;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
