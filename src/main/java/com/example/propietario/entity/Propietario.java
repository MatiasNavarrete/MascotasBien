package com.example.propietario.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "propietarios", indexes = {
        @Index(name = "idx_propietario_email", columnList = "email")
})
@Getter @Setter
@NoArgsConstructor
public class Propietario extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    private String secondaryContactName;
    private String secondaryContactPhone;

    private String address;
}
