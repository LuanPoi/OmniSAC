package dev.luanpoi.omnisacbackend.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.luanpoi.omnisacbackend.models.Address;
import dev.luanpoi.omnisacbackend.models.IdentificationDocument;
import dev.luanpoi.omnisacbackend.models.User;

import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {
    private UUID id;
    private UserDto user;
    private IdentificationDocumentDto identificationDocument;
    private AddressDto defaultAddress;
    private AddressDto billingAddress;
    private List<IdentificationDocumentDto> identificationDocuments;
    private List<AddressDto> addresses;

    public ClientDto() {
    }

    public ClientDto(UUID id, UserDto user, IdentificationDocumentDto identificationDocument, AddressDto defaultAddress, AddressDto billingAddress, List<IdentificationDocumentDto> identificationDocuments, List<AddressDto> addresses) {
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

    public AddressDto getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(AddressDto defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public AddressDto getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(AddressDto billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<IdentificationDocumentDto> getIdentificationDocuments() {
        return identificationDocuments;
    }

    public void setIdentificationDocuments(List<IdentificationDocumentDto> identificationDocuments) {
        this.identificationDocuments = identificationDocuments;
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDto> addresses) {
        this.addresses = addresses;
    }
}
