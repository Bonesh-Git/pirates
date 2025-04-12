package com.meli.cms.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "CFCIFCD")
public class Card {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private UUID cardId;
    @Column(name = "CIFI", nullable = false, columnDefinition = "BIGINT")
    private final Long iin = 610433L;
    @Column(name = "CIFT", nullable = false, columnDefinition = "INTEGER")
    private int type;
    @Column(name = "CIFSL", nullable = false, columnDefinition = "VARCHAR(5)")
    private String serial;
    @Column(name = "CIFPA", nullable = false, columnDefinition = "VARCHAR(15)")
    private String pan = iin + type + serial;
    @Column(name = "CIFCV", nullable = false, columnDefinition = "VARCHAR(4)")
    private String cvv2;
    @Column(name = "CIFEM", nullable = false, columnDefinition = "VARCHAR(2)")
    private String expMonth;
    @Column(name = "CIFEY", nullable = false, columnDefinition = "VARCHAR(4)")
    private String expYear;
    @OneToOne
    private Customer customer;
    @OneToOne
    private Branch branch;
    @OneToOne
    private Deposit deposit;


    public Card(int type, String serial, String cvv2, String expMonth, String expYear, Customer customer, Branch branch, Deposit deposit) {
        this.type = type;
        this.serial = serial;
        this.cvv2 = cvv2;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.customer = customer;
        this.branch = branch;
        this.deposit = deposit;
        this.pan = iin + String.valueOf(type) + serial;
    }

    public Card() {
    }

    public UUID getCardId() {
        return cardId;
    }

    public void setCardId(UUID cardId) {
        this.cardId = cardId;
    }

    public Long getIin() {
        return iin;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getPan() {
        return pan;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }
}