package dev.luanpoi.omnisacbackend.models;

import dev.luanpoi.omnisacbackend.audit.Auditable;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "phone_numbers", schema = "public")
public class PhoneNumber extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(name = "area_code", nullable = false, length = 5)
    private String areaCode;

    @Column(name = "number", nullable = false, length = 50)
    private String number;
}
