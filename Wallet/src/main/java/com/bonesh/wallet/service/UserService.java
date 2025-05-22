package com.bonesh.wallet.service;


import com.bonesh.wallet.entity.User;
import com.bonesh.wallet.entity.Wallet;
import com.bonesh.wallet.exception.UserAlreadyExist;
import com.bonesh.wallet.repository.UserRepository;
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
        user.setWalletId(walletService.save(new Wallet(500000000000000L)));
        if (userRepository.existsUserByUsername(user.getUsername())) {
            throw new UserAlreadyExist(10, "Username already exist");
        }
        return userRepository.save(user).getId();
    }


    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUser(String username, String password) {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.getPassword().equals(password)) throw new RuntimeException("User not found");
        return user;
    }

}
