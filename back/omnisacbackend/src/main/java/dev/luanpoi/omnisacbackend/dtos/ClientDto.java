package dev.luanpoi.omnisacbackend.dtos;

import dev.luanpoi.omnisacbackend.models.Address;
import dev.luanpoi.omnisacbackend.models.IdentificationDocument;
import dev.luanpoi.omnisacbackend.models.User;

import java.util.List;
import java.util.UUID;

public class ClientDto {
    private UUID id;
    private UserDto user;
    private IdentificationDocumentDto identificationDocument;
    private Address defaultAddress;
    private Address billingAddress;
    private List<IdentificationDocumentDto> identificationDocuments;
    private List<Address> addresses;

    public ClientDto() {
    }

    public ClientDto(UUID id, UserDto user, IdentificationDocumentDto identificationDocument, Address defaultAddress, Address billingAddress, List<IdentificationDocumentDto> identificationDocuments, List<Address> addresses) {
        this.id = id;
        this.user = user;
        this.identificationDocument = identificationDocument;
        this.defaultAddress = defaultAddress;
        this.billingAddress = billingAddress;
        this.identificationDocuments = identificationDocuments;
        this.addresses = addresses;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public IdentificationDocumentDto getIdentificationDocument() {
        return identificationDocument;
    }

    public void setIdentificationDocument(IdentificationDocumentDto identificationDocument) {
        this.identificationDocument = identificationDocument;
    }

    public Address getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Address defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<IdentificationDocumentDto> getIdentificationDocuments() {
        return identificationDocuments;
    }

    public void setIdentificationDocuments(List<IdentificationDocumentDto> identificationDocuments) {
        this.identificationDocuments = identificationDocuments;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
