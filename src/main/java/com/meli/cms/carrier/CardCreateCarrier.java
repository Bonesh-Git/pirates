package com.meli.cms.carrier;

import com.meli.cms.entity.CardType;

import java.util.UUID;

public class CardCreateCarrier {

    private final UUID customerId;
    private final CardType cardType;
    private final UUID branchId;
    private final UUID depositId;

    public CardCreateCarrier(UUID customerId, CardType cardType, UUID branchId, UUID depositId) {
        this.customerId = customerId;
        this.cardType = cardType;
        this.branchId = branchId;
        this.depositId = depositId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public CardType getCardType() {
        return cardType;
    }

    public UUID getBranchId() {
        return branchId;
    }

    public UUID getDepositId() {
        return depositId;
    }
}