package com.example.wallet.service;


import com.example.wallet.entity.User;
import com.example.wallet.entity.Wallet;
import com.example.wallet.exception.UserAlreadyExist;
import com.example.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final WalletService walletService;


    @Autowired
    public UserService(UserRepository userRepository, WalletService walletService) {
        this.userRepository = userRepository;
        this.walletService = walletService;
    }

    @Transactional
    public UUID save(User user) {
        user.setWalletId(walletService.save(new Wallet(1000000000000L)));
        if (userRepository.existsUserByUsername(user.getUsername())) {
            throw new UserAlreadyExist(10, "Username already exist");
        }
        return userRepository.save(user).getId();
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow();
    }
}
