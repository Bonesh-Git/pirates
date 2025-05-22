package com.bonesh.wallet.carrier;

import java.util.UUID;

public record TransactionDepositCarrier(UUID SourceWallet , UUID DestinationWallet , Long amount) {
}
