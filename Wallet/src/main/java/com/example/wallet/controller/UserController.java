package com.example.wallet.controller;

import com.example.wallet.carrier.user.UserRegistrationREQCarrier;
import com.example.wallet.carrier.user.UserRegistrationRESCarrier;
import com.example.wallet.entity.User;
import com.example.wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("register")
    public UserRegistrationRESCarrier registerUser(@RequestBody UserRegistrationREQCarrier carrier) {
        return new UserRegistrationRESCarrier(service.save(new User(carrier.fullName(), carrier.mobileNumber(), carrier.username(), carrier.password())));
    }

}
