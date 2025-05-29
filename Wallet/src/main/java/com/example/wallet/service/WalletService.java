package com.example.wallet.service;


import com.example.wallet.entity.Wallet;
import com.example.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private TokenService tokenService;
    @Autowired
    public WalletService(WalletRepository walletRepository, TokenService tokenService) {
        this.walletRepository = walletRepository;
        this.tokenService = tokenService;
    }

    public UUID save(Wallet wallet) {

        return walletRepository.save(wallet).getId();
    }
    public Wallet findById(UUID id) {
        return walletRepository.findById(id).orElseThrow();
    }
    public void update(Wallet wallet) {
        walletRepository.save(wallet);
    }

    public boolean withdrawFromWallet(UUID walletId, long amount, String token) {
        if (!tokenService.isValid(token)) {
            throw new RuntimeException("Invalid token");
        }

        Wallet wallet = findById(walletId);
        boolean success = wallet.withdraw(amount);
        if (!success) {
            throw new RuntimeException("Insufficient balance.");
        }
        update(wallet);
        return true;
    }

}