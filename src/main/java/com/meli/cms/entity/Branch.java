package com.meli.cms.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "CFCIFBR")
public class Branch {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private UUID branchId;
    @Column(name = "BIFLOC", nullable = false, columnDefinition = "VARCHAR(100)")
    private String location;


    public Branch(String location) {
        this.location = location;
    }

    public Branch() {
    }

    public UUID getBranchId() {
        return branchId;
    }

    public void setBranchId(UUID branchId) {
        this.branchId = branchId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}