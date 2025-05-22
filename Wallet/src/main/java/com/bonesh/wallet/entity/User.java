package com.bonesh.wallet.entity;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class User extends Generic{
    @Field
    @NonNull
    private String fullName;
    @Field
    @NonNull
    private String username;
    @Field
    @NonNull
    private String mobilleNumber;
    @Field
    @NonNull
    private String password;
    @Field
    @NonNull
    private UUID walletId;

    public User( @NonNull String fullName, @NonNull String username, @NonNull String mobilleNumber, @NonNull String password) {
        super();
        this.fullName = fullName;
        this.username = username;
        this.mobilleNumber = mobilleNumber;
        this.password = password;
    }

    public @NonNull String getFullName() {
        return fullName;
    }

    public void setFullName(@NonNull String fullName) {
        this.fullName = fullName;
    }

    public @NonNull String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public @NonNull String getMobilleNumber() {
        return mobilleNumber;
    }

    public void setMobilleNumber(@NonNull String mobilleNumber) {
        this.mobilleNumber = mobilleNumber;
    }

    public @NonNull String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public @NonNull UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(@NonNull UUID walletId) {
        this.walletId = walletId;
    }
}
