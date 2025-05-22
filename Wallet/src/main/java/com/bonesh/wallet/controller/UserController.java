package com.bonesh.wallet.controller;



import com.bonesh.wallet.carrier.UserAuthenticationREQCarrier;
import com.bonesh.wallet.carrier.UserAuthenticationRESCarrier;
import com.bonesh.wallet.carrier.UserRegistrationREQCarrier;
import com.bonesh.wallet.carrier.UserRegistrationRESCarrier;
import com.bonesh.wallet.entity.User;
import com.bonesh.wallet.service.UserService;
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

    @GetMapping("authentication")
    public UserAuthenticationRESCarrier authenticationUser(@RequestBody UserAuthenticationREQCarrier carrier) {
        User user = service.getUser(carrier.username(), carrier.password());
       return new UserAuthenticationRESCarrier(user.getUsername());
    }
}
