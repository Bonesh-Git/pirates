package com.meli.cms.service;

import com.meli.cms.carrier.DepositCreateCarrier;
import com.meli.cms.entity.Deposit;
import com.meli.cms.repository.DepositRepository;

import java.util.UUID;


public class DepositService {

    private final DepositRepository depositRepository;

    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    public Deposit createDeposit(DepositCreateCarrier depositCreateCarrier) {
        return depositRepository.save(new Deposit(depositCreateCarrier.getAmount()));

    }

    public Deposit getDeposit(UUID depositId) {
        return depositRepository.findByDepositId(depositId);
    }
}