package dev.luanpoi.omnisacbackend.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryDto {
    private UUID id;
    private String name;
    private String isoCode;
    private String countryCode;
    private String language;
    private String identificationDocumentName;

    public CountryDto() {
    }

    public CountryDto(UUID id, String name, String isoCode, String countryCode, String language, String identificationDocumentName) {
        this.id = id;
        this.name = name;
        this.isoCode = isoCode;
        this.countryCode = countryCode;
        this.language = language;
        this.identificationDocumentName = identificationDocumentName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIdentificationDocumentName() {
        return identificationDocumentName;
    }

    public void setIdentificationDocumentName(String identificationDocumentName) {
        this.identificationDocumentName = identificationDocumentName;
    }
}
