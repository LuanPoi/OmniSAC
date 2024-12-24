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

    public Country() {
    }

    public Country(UUID id, String name, String isoCode, String countryCode, String language, String identificationDocumentName) {
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
