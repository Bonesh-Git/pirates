package com.bonesh.wallet.entity;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Transaction extends Generic{
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
}
