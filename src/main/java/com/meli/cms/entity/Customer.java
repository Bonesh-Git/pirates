package com.meli.cms.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "CFCIFCR")
public class Customer {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private UUID customerId;
    @Column(name = "CIFFNM", nullable = false, columnDefinition = "VARCHAR(50)")
    private String firstname;
    @Column(name = "CIFLNM", nullable = false, columnDefinition = "VARCHAR(100)")
    private String lastname;
    @Column(name = "CIFNID", nullable = false, columnDefinition = "VARCHAR(10)")
    private String nationalId;


    public Customer(String firstname, String lastname, String nationalId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationalId = nationalId;
    }

    public Customer() {

    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}