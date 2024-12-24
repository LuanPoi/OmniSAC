package dev.luanpoi.omnisacbackend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.luanpoi.omnisacbackend.audit.Auditable;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clients", schema = "public")
public class Client extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "identification_document_id", referencedColumnName = "id", nullable = true)
    private IdentificationDocument identificationDocument;

    @OneToOne
    @JoinColumn(name = "default_address_id", referencedColumnName = "id", nullable = true)
    private Address defaultAddress;

    @OneToOne
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id", nullable = true)
    private Address billingAddress;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<IdentificationDocument> identificationDocuments;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Address> addresses;

    public Client() {
    }

    public Client(UUID id, User user, IdentificationDocument identificationDocument, Address defaultAddress, Address billingAddress, List<IdentificationDocument> identificationDocuments, List<Address> addresses) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public IdentificationDocument getIdentificationDocument() {
        return identificationDocument;
    }

    public void setIdentificationDocument(IdentificationDocument identificationDocument) {
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

    public List<IdentificationDocument> getIdentificationDocuments() {
        return identificationDocuments;
    }

    public void setIdentificationDocuments(List<IdentificationDocument> identificationDocuments) {
        this.identificationDocuments = identificationDocuments;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
