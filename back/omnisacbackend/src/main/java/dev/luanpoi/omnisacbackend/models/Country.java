package dev.luanpoi.omnisacbackend.models;

import dev.luanpoi.omnisacbackend.audit.Auditable;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "countries", schema = "public")
public class Country extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "iso_code", unique = true, nullable = false, length = 2)
    private String isoCode;

    @Column(name = "country_code", unique = true, nullable = false, length = 10)
    private String countryCode;

    @Column(name = "language", nullable = false, length = 5)
    private String language;

    @Column(name = "identification_document_name", nullable = false, length = 5)
    private String identificationDocumentName;
}
