package com.example.wallet.entity;

import lombok.*;

import org.springframework.data.couchbase.core.mapping.Field;

import java.util.UUID;

public class User extends GenericEntity {
    @Field
    @NonNull
    private String fullName;
    @Field
    @NonNull
    private String mobileNumber;
    @Field
    @NonNull
    private String username;
    @Field
    @NonNull
    private String password;
    @Field
    private UUID walletId;

    public User() {
    }

    public User(@NonNull String fullName, @NonNull String mobileNumber, @NonNull String username, @NonNull String password, UUID walletId) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.password = password;
        this.walletId = walletId;
    }

    public User(@NonNull String fullName, @NonNull String mobileNumber, @NonNull String username, @NonNull String password) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.password = password;
    }

    public @NonNull String getFullName() {
        return fullName;
    }

    public void setFullName(@NonNull String fullName) {
        this.fullName = fullName;
    }

    public @NonNull String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(@NonNull String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public @NonNull String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public @NonNull String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }
}
