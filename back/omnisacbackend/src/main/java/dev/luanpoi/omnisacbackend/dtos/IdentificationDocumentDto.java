package dev.luanpoi.omnisacbackend.dtos;

import dev.luanpoi.omnisacbackend.models.Client;
import dev.luanpoi.omnisacbackend.models.Country;

import java.util.UUID;

public class IdentificationDocumentDto {
    private UUID id;
    private ClientDto client;
    private CountryDto country;
    private String documentNumber;

    public IdentificationDocumentDto() {
    }

    public IdentificationDocumentDto(UUID id, ClientDto client, CountryDto country, String documentNumber) {
        this.id = id;
        this.client = client;
        this.country = country;
        this.documentNumber = documentNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
