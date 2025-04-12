package com.meli.cms.carrier;

public class DepositCreateCarrier {

    private final double amount;

    public DepositCreateCarrier(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}