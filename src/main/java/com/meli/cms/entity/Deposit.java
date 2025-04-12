package com.meli.cms.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "CFCIFDP")
public class Deposit {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private UUID depositId;
    @Column(name = "DIFAMT", nullable = false, columnDefinition = "DOUBLE PRECISION")
    private double amount;


    public Deposit(double amount) {
        this.amount = amount;
    }

    public Deposit() {

    }

    public UUID getDepositId() {
        return depositId;
    }

    public void setDepositId(UUID depositId) {
        this.depositId = depositId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}