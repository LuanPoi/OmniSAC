package dev.luanpoi.omnisacbackend.models;

import dev.luanpoi.omnisacbackend.audit.Auditable;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "addresses", schema = "public")
public class Address extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = true, length = 10)
    private String number;

    @Column(name = "complement", nullable = true)
    private String complement;

    @Column(name = "neighborhood", nullable = true)
    private String neighborhood;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;
}
