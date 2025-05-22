package com.bonesh.wallet.service;


import com.bonesh.wallet.carrier.TransactionBalanceRequest;
import com.bonesh.wallet.carrier.TransactionDepositCarrier;
import com.bonesh.wallet.carrier.WithdrawCarrier;
import com.bonesh.wallet.entity.*;
import com.bonesh.wallet.exception.NotSufficientFund;
import com.bonesh.wallet.exception.SourceAndDestinationAreSameException;
import com.bonesh.wallet.exception.TransactionNotAllowedException;
import com.bonesh.wallet.repository.TransactionRepository;
import com.bonesh.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TransactionService {
    public final WalletService walletService;
    public final TransactionRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public TransactionService(WalletService walletService, TransactionRepository repository, UserRepository userRepository) {
        this.walletService = walletService;
        this.repository = repository;
        this.userRepository = userRepository;
    }
    @Transactional
    public void deposit(TransactionDepositCarrier carrier) {
       Wallet source =  walletService.findById(carrier.SourceWallet());
       Wallet destination =  walletService.findById(carrier.DestinationWallet());
       if (source.getId().equals(destination.getId())) {
           throw new SourceAndDestinationAreSameException(20,"Source and destination are the same");
       } else if (source.getStatus().equals(WalletStatus.BANNED)||destination.getStatus().equals(WalletStatus.BANNED)||source.getStatus().equals(WalletStatus.BLOCKED)||destination.getStatus().equals(WalletStatus.BLOCKED)) {
           throw new TransactionNotAllowedException(30,"Transaction is not allowed");
       } else if (source.getBalance()< carrier.amount()) {
           throw new NotSufficientFund(40,"Not sufficient fund");
       }
       source.setBalance(source.getBalance()-carrier.amount());
       destination.setBalance(destination.getBalance()+carrier.amount());
       walletService.save(source);
       walletService.save(destination);
       repository.save(new Transaction(TransactionType.DEBIT,carrier.amount(),carrier.SourceWallet(),carrier.DestinationWallet()));
       repository.save(new Transaction(TransactionType.CREDIT,carrier.amount(),carrier.DestinationWallet(),carrier.SourceWallet()));

    }

    public void withdraw(WithdrawCarrier carrier) {
        Wallet source =  walletService.findById(carrier.walletId());
         if (source.getBalance() == 0) {
             throw new NotSufficientFund(40,"Withdraw amount can not be zero");
         }
         if(source.getBalance()<carrier.amount()) {
             throw new NotSufficientFund(40,"Not sufficient fund");
         }
         source.setBalance(source.getBalance()-carrier.amount());
         walletService.save(source);
    }

    public Long getBalance(TransactionBalanceRequest request) {
       return walletService.findById(request.walletId()).getBalance();
    }
}
