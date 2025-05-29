package com.example.wallet.entity;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.List;
import java.util.UUID;

public class Wallet extends GenericEntity {
    @Field
    @NonNull
    private long balance;
    @Field
    private List<UUID> transactionList;
    @Field
    private WalletStatus status = WalletStatus.NORMAL;


    public boolean withdraw(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (this.balance < amount) {
            return false;
        }
        this.balance -= amount;
        return true;
    }

    public Wallet() {
    }

    public Wallet(@NonNull long balance, List<UUID> transactionList, WalletStatus status) {
        this.balance = balance;
        this.transactionList = transactionList;
        this.status = status;
    }

    public Wallet(@NonNull long balance) {
        this.balance = balance;
    }

    @NonNull
    public long getBalance() {
        return balance;
    }

    public void setBalance(@NonNull long balance) {
        this.balance = balance;
    }

    public List<UUID> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<UUID> transactionList) {
        this.transactionList = transactionList;
    }

    public WalletStatus getStatus() {
        return status;
    }

    public void setStatus(WalletStatus status) {
        this.status = status;
    }
}