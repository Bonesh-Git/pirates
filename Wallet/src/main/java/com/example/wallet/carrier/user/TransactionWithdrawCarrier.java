package com.example.wallet.carrier.user;

import java.util.UUID;

public record TransactionWithdrawCarrier(UUID SourceWallet, UUID DestinationWallet, Long amount) {
}
