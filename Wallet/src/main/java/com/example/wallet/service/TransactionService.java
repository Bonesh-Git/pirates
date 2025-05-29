package com.example.wallet.service;

import com.example.wallet.carrier.user.TransactionBalanceRequest;
import com.example.wallet.carrier.user.TransactionDepositCarrier;
import com.example.wallet.entity.Transaction;
import com.example.wallet.entity.TransactionType;
import com.example.wallet.entity.Wallet;
import com.example.wallet.entity.WalletStatus;
import com.example.wallet.exception.NotSufficientFund;
import com.example.wallet.exception.SourceAndDestinationAreSameException;
import com.example.wallet.exception.TransactionNotAllowedException;
import com.example.wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {
    public final WalletService walletService;
    public final TransactionRepository repository;

    @Autowired
    public TransactionService(WalletService walletService, TransactionRepository repository) {
        this.walletService = walletService;
        this.repository = repository;
    }

    @Transactional
    public void deposit(TransactionDepositCarrier carrier) {
        Wallet source = walletService.findById(carrier.SourceWallet());
        Wallet destination = walletService.findById(carrier.DestinationWallet());
        if (source.getId().equals(destination.getId())) {
            throw new SourceAndDestinationAreSameException(20, "Source and destination are the same");
        } else if (source.getStatus().equals(WalletStatus.BANNED) || destination.getStatus().equals(WalletStatus.BANNED) || source.getStatus().equals(WalletStatus.BLOCKED) || destination.getStatus().equals(WalletStatus.BLOCKED)) {
            throw new TransactionNotAllowedException(30, "Transaction is not allowed");
        } else if (source.getBalance() < carrier.amount()) {
            throw new NotSufficientFund(40, "Not sufficient fund");
        }
        source.setBalance(source.getBalance() - carrier.amount());
        destination.setBalance(destination.getBalance() + carrier.amount());
        walletService.save(source);
        walletService.save(destination);
        repository.save(new Transaction(TransactionType.DEBIT, carrier.amount(), carrier.SourceWallet(), carrier.DestinationWallet()));
        repository.save(new Transaction(TransactionType.CREDIT, carrier.amount(), carrier.DestinationWallet(), carrier.SourceWallet()));

    }

    public Long getBalance(TransactionBalanceRequest request) {
        return walletService.findById(request.walletId()).getBalance();
    }
}
