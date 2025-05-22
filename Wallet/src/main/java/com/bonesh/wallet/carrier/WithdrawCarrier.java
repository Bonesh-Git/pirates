package com.bonesh.wallet.carrier;

import java.util.UUID;

public record WithdrawCarrier(UUID walletId, Long amount) {
}
