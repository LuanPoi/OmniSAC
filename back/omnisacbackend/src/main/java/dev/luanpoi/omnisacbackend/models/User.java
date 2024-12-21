package dev.luanpoi.omnisacbackend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.luanpoi.omnisacbackend.audit.Auditable;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "public")
public class User extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "default_phone_number_id", referencedColumnName = "id", nullable = true)
    private PhoneNumber defaultPhoneNumber;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PhoneNumber> phoneNumbers;
}
