package com.bonesh.wallet.controller;


import com.bonesh.wallet.carrier.*;
import com.bonesh.wallet.entity.User;
import com.bonesh.wallet.service.TransactionService;
import com.bonesh.wallet.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionService tService;
    private final UserService uService;

    public TransactionController(TransactionService service, TransactionService tService, UserService uService) {
        this.tService = tService;
        this.uService = uService;
    }

    @PostMapping
    public void deposit(@RequestBody UserTransactionRequestCarrier carrier) {
        User source = uService.getUserByUsername(carrier.sourceUsername());
        User destination = uService.getUserByUsername(carrier.destinationUsername());
        tService.deposit(new TransactionDepositCarrier(source.getWalletId(), destination.getWalletId(), carrier.amount()));
    }
    @GetMapping("/balance")
    public Long getBalance(@RequestBody UserGetWalletBalanceCarrier carrier) {
        User user = uService.getUserByUsername(carrier.username());
        return tService.getBalance(new TransactionBalanceRequest(user.getWalletId()));
    }

    @PostMapping
    public void withdraw(@RequestBody UserTransactionRequestCarrier carrier) {
        User source = uService.getUserByUsername(carrier.sourceUsername());
        tService.withdraw(new WithdrawCarrier(source.getWalletId(), carrier.amount()));
    }
}
