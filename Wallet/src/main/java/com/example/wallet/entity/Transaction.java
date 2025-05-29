package com.example.wallet.entity;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.UUID;

public class Transaction extends GenericEntity {
    @Field
    @NonNull
    private TransactionType type;
    @Field
    private long amount;
    @Field
    @NonNull
    private UUID sourceWallet;
    @Field
    @NonNull
    private UUID destinationWallet;

    public Transaction(@NonNull TransactionType type, long amount, @NonNull UUID sourceWallet, @NonNull UUID destinationWallet) {
        this.type = type;
        this.amount = amount;
        this.sourceWallet = sourceWallet;
        this.destinationWallet = destinationWallet;
    }

    public Transaction(@NonNull TransactionType type, @NonNull UUID sourceWallet, @NonNull UUID destinationWallet) {
        this.type = type;
        this.sourceWallet = sourceWallet;
        this.destinationWallet = destinationWallet;
    }

    public Transaction() {
    }

    public @NonNull TransactionType getType() {
        return type;
    }

    public void setType(@NonNull TransactionType type) {
        this.type = type;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public @NonNull UUID getSourceWallet() {
        return sourceWallet;
    }

    public void setSourceWallet(@NonNull UUID sourceWallet) {
        this.sourceWallet = sourceWallet;
    }

    public @NonNull UUID getDestinationWallet() {
        return destinationWallet;
    }

    public void setDestinationWallet(@NonNull UUID destinationWallet) {
        this.destinationWallet = destinationWallet;
    }
}
