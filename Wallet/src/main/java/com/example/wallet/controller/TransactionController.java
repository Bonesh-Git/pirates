package com.example.wallet.controller;

import com.example.wallet.carrier.user.TransactionBalanceRequest;
import com.example.wallet.carrier.user.TransactionDepositCarrier;
import com.example.wallet.carrier.user.UserGetWalletBalanceCarrier;
import com.example.wallet.carrier.user.UserTransactionRequestCarrier;
import com.example.wallet.entity.User;
import com.example.wallet.service.TokenService;
import com.example.wallet.service.TransactionService;
import com.example.wallet.service.UserService;
import com.example.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    private final TransactionService tService;
    private final UserService uService;
    private final WalletService walletService;

    @Autowired
    public TransactionController(TransactionService service, TransactionService tService,
                                 UserService uService, WalletService walletService) {
        this.tService = tService;
        this.uService = uService;
        this.walletService = walletService;
    }


    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody UserTransactionRequestCarrier carrier,
                                          @RequestHeader("Authorization") String token) {
        if (!TokenService.isValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        User source = uService.getUserByUsername(carrier.sourceUsername());
        User destination = uService.getUserByUsername(carrier.destinationUsername());
        tService.deposit(new TransactionDepositCarrier(source.getWalletId(), destination.getWalletId(), carrier.amount()));
        return ResponseEntity.ok("Deposit successful");
    }


    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(@RequestBody UserGetWalletBalanceCarrier carrier,
                                        @RequestHeader("Authorization") String token) {
        if (!TokenService.isValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        User user = uService.getUserByUsername(carrier.username());
        Long balance = tService.getBalance(new TransactionBalanceRequest(user.getWalletId()));
        return ResponseEntity.ok(balance);
    }


    @PostMapping("/{id}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable UUID id,
                                           @RequestParam long amount,
                                           @RequestHeader("Authorization") String token) {
        if (!TokenService.isValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        try {
            walletService.withdrawFromWallet(id, amount, token);
            return ResponseEntity.ok("Withdrawal successful");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
