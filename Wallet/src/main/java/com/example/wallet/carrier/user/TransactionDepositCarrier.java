package com.example.wallet.carrier.user;

import java.util.UUID;

public record TransactionDepositCarrier(UUID SourceWallet , UUID DestinationWallet , Long amount) {
}
