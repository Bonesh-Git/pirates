package com.bonesh.wallet.entity;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Wallet extends Generic {
    @Field
    @NonNull
    private long balance;
    @Field
    private List<UUID> transactionList;
    @Field
    private WalletStatus status = WalletStatus.NORMAL;

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

    public Wallet(@NonNull long balance) {
        this.balance = balance;
    }
}
