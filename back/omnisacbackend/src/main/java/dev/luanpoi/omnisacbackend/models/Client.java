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
}
